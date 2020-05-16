package com.example.shiftmanagment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.util.Employee;
import com.example.shiftmanagment.viewmodel.MainActivityViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private MainActivityViewModel viewModel = new MainActivityViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ProgressBar progressBar = findViewById(R.id.progressBarSplashScreen);
        progressBar.setVisibility(View.VISIBLE);

        if(mAuth.getCurrentUser() != null){
            viewModel.isManager(new MainActivity.isManagerCallback() {
                @Override
                public void moveToNextPage(Employee employee) {
                    if(employee.isManager()){
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
            });
        }
        else{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
