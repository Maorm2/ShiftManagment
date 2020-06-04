package com.example.shiftmanagment.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.adapter.EmployeeAdapter;
import com.example.shiftmanagment.util.Employee;
import com.example.shiftmanagment.viewmodel.ManageEmployeeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ManageEmployeeFragment extends Fragment{

    private ManageEmployeeViewModel viewModel = new ManageEmployeeViewModel();
    private CallbackFragment callbackFragment;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_employee_list,container,false);

        recyclerView = rootView.findViewById(R.id.recyclerview_employee);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadEmployeeList();


        FloatingActionButton addNewEmployee = rootView.findViewById(R.id.addNewEmployee_fab);
        addNewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callbackFragment!= null){
                    callbackFragment.changeFragment();
                }
            }
        });


        return rootView;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment){
        this.callbackFragment = callbackFragment;
    }

    public void loadEmployeeList(){

        viewModel.loadEmployees(new loadEmployeeList() {
            @Override
            public void setEmployeeList(List<Employee> employeeList) {
                initEmployeeAdapter(employeeList);
            }
        });

    }

    public void initEmployeeAdapter(List<Employee> employeeList){
        EmployeeAdapter employeeAdapter = new EmployeeAdapter(employeeList);
        recyclerView.setAdapter(employeeAdapter);
    }

    public interface loadEmployeeList{
        void setEmployeeList(List<Employee> employeeList);
    }



}
