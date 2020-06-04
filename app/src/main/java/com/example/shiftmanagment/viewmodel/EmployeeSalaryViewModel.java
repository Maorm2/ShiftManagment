package com.example.shiftmanagment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;
import com.example.shiftmanagment.view.EmployeeSalaryView;

public class EmployeeSalaryViewModel extends ViewModel {

    private Database database = Database.getInstance();

    public void getShifts(EmployeeSalaryView.Callback callback){

    }

}
