package com.example.shiftmanagment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;
import com.example.shiftmanagment.util.PoolUser;
import com.example.shiftmanagment.view.ManageShiftsView;

import java.text.ParseException;
import java.util.HashMap;

public class ManageShiftsViewModel extends ViewModel {

    private Database database = Database.getInstance();

    public void getList(ManageShiftsView.onCallback onCallback) {
        database.getList(onCallback);
    }

    public void shiftsApproved(HashMap<String, String> shifts,PoolUser user) throws ParseException {
        database.shiftsApprove(shifts,user);
    }
}
