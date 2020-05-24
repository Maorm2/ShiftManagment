package com.example.shiftmanagment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

        FloatingActionButton addNewEmployee = rootView.findViewById(R.id.addNewEmployee_fab);
        addNewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callbackFragment!= null){
                    callbackFragment.changeFragment();
                }

            }
        });


      //  recyclerView.notifyAll();

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
