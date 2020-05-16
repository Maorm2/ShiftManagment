package com.example.shiftmanagment.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.adapter.EmployeeAdapter;
import com.example.shiftmanagment.fragment.CallbackFragment;
import com.example.shiftmanagment.fragment.NewEmployeeFragment;
import com.example.shiftmanagment.util.Employee;
import com.example.shiftmanagment.viewmodel.ManageEmployeeViewModel;

import java.util.ArrayList;
import java.util.List;

public class ManageEmployeeFragment extends Fragment{

    private ManageEmployeeViewModel viewModel = new ManageEmployeeViewModel();
    private CallbackFragment callbackFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_employee_list,container,false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview_employee);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Employee> employees = loadList();
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));
        employees.add(new Employee("Maor@gmmail.com","123445","Maor","Minyan",5000,true));

        EmployeeAdapter employeeAdapter = new EmployeeAdapter(employees);
        recyclerView.setAdapter(employeeAdapter);

        Button addNewEmployee = rootView.findViewById(R.id.addNewEmployeeBtn);
        addNewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callbackFragment!= null){
                    callbackFragment.changeFragment();
                }
            }
        });

//        recyclerView.notifyAll();

        return rootView;
    }


    public void setCallbackFragment(CallbackFragment callbackFragment){
        this.callbackFragment = callbackFragment;
    }

    public List<Employee> loadList(){
        List<Employee> list =  viewModel.loadEmployees();
        return list;
    }


}
