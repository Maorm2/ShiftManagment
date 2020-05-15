package com.example.shiftmanagment.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.util.Employee;
import com.example.shiftmanagment.viewmodel.MainActivityViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authStateListener;
    private MainActivityViewModel viewModel = new MainActivityViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    
                }
            }
        };

        ImageView btnLogIn = findViewById(R.id.loginBtn);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextUserName = findViewById(R.id.text_user_name);
                EditText editTextPassword = findViewById(R.id.text_password);
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                viewModel.signInUser(userName, password, new LogInActions() {
                    @Override
                    public void LogInSuccessfully(Employee user) {
                        Log.d("mylog ", user.toString());
                        if(user.isManager()){
                            Intent intent = new Intent(getApplicationContext(), AdminPageView.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), EmployeePageView.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void LogInFailed() {
                        Toast toast = Toast.makeText(MainActivity.this,
                                "Wrong User Name Or Password!",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        });



//        Shift shift = new Shift(new Time(20,0,0), new Time(23,0,0));
//        Log.d("Log",String.valueOf(shift.getDuration()));


    }





    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
    
    public interface LogInActions {
        void LogInSuccessfully(Employee user);
        void LogInFailed();
    }
}
