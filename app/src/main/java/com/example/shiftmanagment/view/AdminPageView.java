package com.example.shiftmanagment.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.developer.kalert.KAlertDialog;
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

       final Button btnMoveToMngEmployee = findViewById(R.id.manageEmployeesBtn);
        btnMoveToMngEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMoveToMngEmployee.setBackgroundResource(R.drawable.button_style);
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
        ManageEmployeeFragment fragment = new ManageEmployeeFragment();
        fragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_base_container,fragment);
        transaction.commit();
    }

    public void replaceFragment(){
        fragment = new NewEmployeeFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null)
        .replace(R.id.fragment_base_container,fragment)
        .commit();
    }

    @Override
    public void changeFragment() {
        replaceFragment();
    }

}
