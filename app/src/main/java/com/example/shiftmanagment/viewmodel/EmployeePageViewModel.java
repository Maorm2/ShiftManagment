package com.example.shiftmanagment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;
import com.example.shiftmanagment.view.ManageShiftsView;

public class EmployeePageViewModel extends ViewModel {

    private Database database = Database.getInstance();

    //Methods:
    public void signOut(){
        database.signOutUser();
    }

    public void getPublishShifts(ManageShiftsView.OnCallbackShifts callbackShifts) {
        database.getPublishShifts(callbackShifts);
    }
}
