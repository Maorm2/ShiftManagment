package com.example.shiftmanagment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;
import com.example.shiftmanagment.util.Employee;
import com.example.shiftmanagment.util.PoolUser;
import com.example.shiftmanagment.util.WeekShift;
import com.example.shiftmanagment.view.EmployeeShiftView;
import com.example.shiftmanagment.view.ManageShiftsView;

import org.threeten.bp.LocalDate;

public class EmployeeShiftViewModel extends ViewModel {

    private Database db = Database.getInstance();
    private WeekShift weekShift;
    private PoolUser user;

    public EmployeeShiftViewModel() {
        weekShift = new WeekShift();
        user = new PoolUser();
        db.getCurrentEmpFromDb(new OnDataRetrieve() {
            @Override
            public void onEmployee(Employee emp, String uId) {
                user.setFirstName(emp.getFirstName());
                user.setLastName(emp.getLastName());
                user.setUid(uId);
                user.setWeekShift(weekShift);
            }
        });


    }

    public void addShiftToWeek(String date, String shiftTime){
        weekShift.addToShifts(date, shiftTime);
    }

    public void addWeekShiftToDb( EmployeeShiftView.Callback callBack ){
        db.addWeekShiftToPool(user, callBack);
    }

    public void getPublishShifts(ManageShiftsView.OnCallbackShifts callbackShifts) {
        db.getPublishShifts(callbackShifts);
    }

    public interface OnDataRetrieve{
        void onEmployee(Employee emp, String uId);
    }

}


