package com.example.shiftmanagment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.developer.kalert.KAlertDialog;
import com.example.shiftmanagment.R;
import com.example.shiftmanagment.viewmodel.EmployeePageViewModel;

public class EmployeePageView extends AppCompatActivity {

    private EmployeePageViewModel viewModel = new EmployeePageViewModel();
    private Boolean mShiftsPublished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page_view);

        Button btnSignOut = findViewById(R.id.logOutEmployeeBtn);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.signOut();
              moveToNewActivity(MainActivity.class);
            }
        });

        Button btnMoveToMyShifts = findViewById(R.id.myShiftsBtn);
        btnMoveToMyShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfShiftsPublished();
            }
        });

        Button btnMoveTOMySalary = findViewById(R.id.mySalaryBtn);
        btnMoveTOMySalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToNewActivity(EmployeeSalaryView.class);
            }
        });

        Button btnMoveToViewShifts = findViewById(R.id.viewShiftsBtn);
        btnMoveToViewShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               moveToNewActivity(EmployeeViewShiftsView.class);
            }
        });
    }

    private void checkIfShiftsPublished() {
        viewModel.getPublishShifts(new ManageShiftsView.OnCallbackShifts() {
            @Override
            public void setPublishShifts(boolean isPublish) {
                mShiftsPublished = isPublish;
                if(!mShiftsPublished){
                    moveToNewActivity(EmployeeShiftView.class);
                }
                else {
                    createInfoDialog();
                }
            }
        });

    }

    private void createInfoDialog(){
        new KAlertDialog(this, KAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.shifts_published_title))
                .setContentText(getString(R.string.shifts_published_instructions))
                .setConfirmText(getString(R.string.confirm_button))
                .confirmButtonColor(R.color.colorPrimary)
                .show();
    }

    private void moveToNewActivity (Class destActivity) {
        Intent i = new Intent(this, destActivity);
        startActivity(i);
    }
}
