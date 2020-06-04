package com.example.shiftmanagment.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.fragment.CallbackFragment;
import com.example.shiftmanagment.fragment.ManageEmployeeFragment;
import com.example.shiftmanagment.fragment.NewEmployeeFragment;
import com.example.shiftmanagment.viewmodel.AdminPageViewModel;

public class AdminPageView extends AppCompatActivity implements CallbackFragment {

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    private AdminPageViewModel viewModel = new AdminPageViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page_view);


        Button btnSignOut = findViewById(R.id.logOutAdminBtn);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.signOut();
                Intent intent = new Intent(AdminPageView.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnMoveToMngEmployee = findViewById(R.id.manageEmployeesBtn);
        btnMoveToMngEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addFragment();
            }
        });

        Button btnMoveToManageShift = findViewById(R.id.btn_manage_shift);
        btnMoveToManageShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPageView.this, ManageShiftsView.class);
                startActivity(intent);
            }
        });
    }

    public void addFragment(){
        setContentView(R.layout.add_employee_base_fragment);
        ManageEmployeeFragment fragment = new ManageEmployeeFragment();
        fragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainer,fragment);
        transaction.commit();
    }

    public void replaceFragment(){
        fragment = new NewEmployeeFragment();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragmentContainer,fragment);
        transaction.commit();
    }

    @Override
    public void changeFragment() {
        replaceFragment();
    }

}
