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
    private Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page_view);

        btnSignOut = findViewById(R.id.logOutEmployeeBtn);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.signOut();
                Intent intent = new Intent(EmployeePageView.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
