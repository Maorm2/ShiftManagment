package com.example.shiftmanagment.database;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;

import com.example.shiftmanagment.fragment.ManageEmployeeFragment;
import com.example.shiftmanagment.util.Employee;
import com.example.shiftmanagment.util.PoolUser;
import com.example.shiftmanagment.util.Shift;
import com.example.shiftmanagment.util.WeekShift;
import com.example.shiftmanagment.view.EmployeeShiftView;
import com.example.shiftmanagment.view.MainActivity;
import com.example.shiftmanagment.view.ManageShiftsView;
import com.example.shiftmanagment.viewmodel.EmployeeShiftViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import org.threeten.bp.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Database {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference mCollRef;
    private DocumentReference mDocRef;
    private String userID;


    private static Database instance = null;
    private Database(){}

    public static Database getInstance(){
        if(instance == null)
            instance = new Database();
        return instance;
    }


    //sign up users
    public void createUser(final Employee employee, final MainActivity.registerActions registerActions){
        final String username = employee.getEmail();
        final String password = employee.getPassword();
        mAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("onComplete","Arrive");
                    userID = mAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("users").document(userID);
                    documentReference.set(employee).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    registerActions.registerSucceed(true);
                }
                else
                {
                    registerActions.registerSucceed(false);
                }
            }
        });
    }

    public void isManager(final MainActivity.isManagerCallback callback){
        mAuth = FirebaseAuth.getInstance();
        DocumentReference mDocRef = db.document("users/" + mAuth.getUid());
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Employee loggedInUser = documentSnapshot.toObject(Employee.class);
                callback.moveToNextPage(loggedInUser);
            }
        });

    }

    //sign in the user
    public void signInUser(final String username,final String password , final MainActivity.LogInActions logInActions){
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mAuth = FirebaseAuth.getInstance();
                    final DocumentReference mDocRef = db.document("users/" + mAuth.getUid());
                    mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Employee loggedInUser = documentSnapshot.toObject(Employee.class);
                            logInActions.LogInSuccessfully(loggedInUser);
                        }
                    });
                }

                else
                {
                    logInActions.LogInFailed();
                }
            }
        });
    }

    //sign out the user
    public void signOutUser(){
        mAuth.signOut();
    }

    public void loadEmployees(final ManageEmployeeFragment.loadEmployeeList loadEmployeeList){
        final List<Employee> list = new ArrayList<>();
        db.collection("users/").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.toObject(Employee.class));
                    }
                    loadEmployeeList.setEmployeeList(list);
                }
            }
        });

    }

    public String getUserID(){
        return mAuth.getUid();
    }

    public void addWeekShiftToPool(PoolUser user, final EmployeeShiftView.Callback callback){
        db.collection("pool").document(user.getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                callback.onSuccess();
            }
        });
    }

    public void getCurrentEmpFromDb(final EmployeeShiftViewModel.OnDataRetrieve callBack){
        final Employee emp;
        db.collection("users").document(mAuth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Employee emp = documentSnapshot.toObject(Employee.class);
                callBack.onEmployee(emp , mAuth.getUid());
            }
        });
    }


    public void getList(final ManageShiftsView.onCallback onCallback) {

        db.collection("pool").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
              List<PoolUser> emp = queryDocumentSnapshots.toObjects(PoolUser.class);
              onCallback.setRequestList(emp);
            }
        });
    }

    public void shiftsApprove(HashMap<String, String> shifts, PoolUser user) {

        WriteBatch batch = db.batch();

        for(Map.Entry <String,String> entry : shifts.entrySet()){
            String date = entry.getKey();
            String shift = entry.getValue();

            HashMap <String, Object> map = new HashMap<>();

            map.put("date", date);
            map.put("shift", shift);
            map.put("timeStamp", new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));

            db.collection("users").document(user.getUid()).collection("shifts")
                    .document(date).set(map);
         //   batch.set(mDocRef,new Shift((date),shift));

        }

      /*  batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: Shifts successfully inserted to DB" );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFilure: Shifts unsuccessfully inserted to DB" );
            }
        });*/

    }
}
