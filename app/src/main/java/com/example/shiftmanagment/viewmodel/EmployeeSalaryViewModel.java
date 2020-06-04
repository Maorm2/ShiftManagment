package com.example.shiftmanagment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;
import com.example.shiftmanagment.view.EmployeeSalaryView;

public class EmployeeSalaryViewModel extends ViewModel {

    private Database db = Database.getInstance();

    public void getShifts(String fromDate, String toDate, EmployeeSalaryView.Callback callback){
        db.getShiftByDate(fromDate, toDate, callback);
    }

}
