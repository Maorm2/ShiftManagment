package com.example.shiftmanagment.viewmodel;


import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;
import com.example.shiftmanagment.util.Employee;
import com.example.shiftmanagment.view.MainActivity;

import java.util.List;

public class ManageEmployeeViewModel extends ViewModel {

    private Database mDatabase = Database.getInstance();

    public void signUpUser(Employee employee, MainActivity.registerActions registerActions){
        mDatabase.createUser(employee,registerActions);
    }

    public List<Employee> loadEmployees(){
       List<Employee> list = mDatabase.loadEmployees();
       Log.d("list View", list.size()+"");
        return list;
    }

}
