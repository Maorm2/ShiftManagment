package com.example.shiftmanagment.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.shiftmanagment.util.Employee;
import com.example.shiftmanagment.view.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
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

    public List<Employee> loadEmployees(){
        final List<Employee> list = new ArrayList<>();
        db.collection("users/").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        list.add(document.toObject(Employee.class));
                    }
                    Log.d("list:" ,list.toString());
                   // Log.d("list(0)", list.get(0)+"");
                }
                else
                    Log.d("list:", "error");

            }
        });

        return list;
    }

}
