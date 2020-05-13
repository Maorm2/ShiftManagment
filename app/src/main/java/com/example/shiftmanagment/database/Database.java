package com.example.shiftmanagment.database;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Database {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private static Database instance = null;
    private Database(){}

    public static Database getInstance(){
        if(instance == null)
            instance = new Database();
        return instance;
    }


    //sign up users
    public void createUser(final String username,final String password,final View v){
        mAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Snackbar.make(v,"Registration succeed",Snackbar.LENGTH_LONG);
                }
                else
                {
                    Snackbar.make(v,"Registration Failed",Snackbar.LENGTH_LONG);
                }
            }
        });
    }

    //sign in the user
    public void signInUser(final String username,final String password,final View v){
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Snackbar.make(v,"Login Successful",Snackbar.LENGTH_LONG).show();
                }
                else
                {
                   Snackbar.make(v,"Wrong username or password",Snackbar.LENGTH_LONG).show();

                }
            }
        });
    }

    //sign out the user
    public void signOutUser(){
        mAuth.signOut();
    }

}
