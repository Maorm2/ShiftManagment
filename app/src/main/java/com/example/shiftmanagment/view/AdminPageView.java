package com.example.shiftmanagment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.viewmodel.ManageEmployeeViewModel;

public class AdminPageView extends AppCompatActivity {

    ManageEmployeeViewModel manageEmployeeViewModel = new ManageEmployeeViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page_view);

        final EditText employeeEmail = findViewById(R.id.emailInput_register);
        final EditText employeePass = findViewById(R.id.passwordInput_register);

        Button addEmployee = findViewById(R.id.registerBtn);
        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = employeeEmail.getText().toString();
                String password = employeePass.getText().toString();
                manageEmployeeViewModel.signUpUser(email,password,v);
            }
        });

    }
}
