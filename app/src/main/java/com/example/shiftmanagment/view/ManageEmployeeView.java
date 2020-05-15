package com.example.shiftmanagment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.adapter.EmployeeAdapter;
import com.example.shiftmanagment.util.Employee;

import java.util.ArrayList;
import java.util.List;

public class ManageEmployeeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_employee);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Maor","Minyan",5000,true));
        employees.add(new Employee("Maor","Minyan",5000,true));
        employees.add(new Employee("Maor","Minyan",5000,true));
        employees.add(new Employee("Maor","Minyan",5000,true));
        employees.add(new Employee("Maor","Minyan",5000,true));
        employees.add(new Employee("Maor","Minyan",5000,true));
        employees.add(new Employee("Maor","Minyan",5000,true));
        employees.add(new Employee("Maor","Minyan",5000,true));

        EmployeeAdapter employeeAdapter = new EmployeeAdapter(employees);
        recyclerView.setAdapter(employeeAdapter);
    }
}
