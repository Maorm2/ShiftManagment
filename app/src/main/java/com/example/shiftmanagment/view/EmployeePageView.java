package com.example.shiftmanagment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.viewmodel.EmployeePageViewModel;

public class EmployeePageView extends AppCompatActivity {

    private EmployeePageViewModel viewModel = new EmployeePageViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page_view);

        Button btnSignOut = findViewById(R.id.logOutEmployeeBtn);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.signOut();
                Intent intent = new Intent(EmployeePageView.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnMoveToMyShifts = findViewById(R.id.myShiftsBtn);
        btnMoveToMyShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeePageView.this, EmployeeShiftView.class);
                startActivity(intent);
            }
        });

        Button btnMoveTOMySalary = findViewById(R.id.mySalaryBtn);
        btnMoveTOMySalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeePageView.this, EmployeeSalaryView.class);
                startActivity(intent);
            }
        });

        Button btnMoveToViewShifts = findViewById(R.id.viewShiftsBtn);
        btnMoveToViewShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeePageView.this, EmployeeViewShiftsView.class);
                startActivity(intent);
            }
        });
    }
}
