package com.example.shiftmanagment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.util.Employee;
import com.example.shiftmanagment.view.AdminPageView;
import com.example.shiftmanagment.view.MainActivity;
import com.example.shiftmanagment.viewmodel.ManageEmployeeViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class NewEmployeeFragment extends Fragment {

    private ManageEmployeeViewModel viewModel = new ManageEmployeeViewModel();
    private CallbackFragment callbackFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.add_new_employee,container,false);

        final EditText firstNameEt = view.findViewById(R.id.employee_first_name_Et);
        final EditText lastNameEt = view.findViewById(R.id.employee_last_name_Et);
        final EditText emailEt = view.findViewById(R.id.employee_mail_Et);
        final EditText passwordEt = view.findViewById(R.id.employee_password_Et);
        final EditText salaryPerHourEt = view.findViewById(R.id.salary_per_hour_employee_Et);
        final CheckBox isAdminCb = view.findViewById(R.id.isAdmin_Cb);

        final ImageView addNewEmployeeBtn = view.findViewById(R.id.add_new_employee_Btn);
        addNewEmployeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String email = emailEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();
                String firstName = firstNameEt.getText().toString().trim();
                String lastName = lastNameEt.getText().toString().trim();
                double salaryPerHour = Double.parseDouble(salaryPerHourEt.getText().toString().trim());

                Employee employee = new Employee(email,password,firstName,lastName,salaryPerHour,isAdminCb.isChecked());
                Log.d("addNewEmployeeBtn","Arrive") ;
                viewModel.signUpUser(employee, new MainActivity.registerActions() {
                    @Override
                    public void registerSucceed(boolean succeed) {
                        if(succeed){
                            moveToNewActivity();
                            Toast.makeText(getContext(), "Register succeed", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Snackbar.make(v, "register NOT SUCCEED", BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                    }

                });
            }
        });

        return view;
    }


    private void moveToNewActivity () {
        Intent i = new Intent(getActivity(), AdminPageView.class);
        startActivity(i);
        getActivity().overridePendingTransition(0, 0);
        getActivity().finish();

    }
}


