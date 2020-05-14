package com.example.shiftmanagment.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.util.Shift;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Date;
import java.sql.Time;
import java.time.Clock;
import java.time.Period;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

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
}
