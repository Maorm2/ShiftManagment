package com.example.shiftmanagment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;
import com.example.shiftmanagment.view.MainActivity;

public class MainActivityViewModel extends ViewModel {

    private  Database mDatabase = Database.getInstance();


    public void isManager(MainActivity.isManagerCallback callback){
        mDatabase.isManager(callback);
    }

    public void signInUser(final String username,final String password , final MainActivity.LogInActions logInActions){
        mDatabase.signInUser(username, password, logInActions);
    }

}
