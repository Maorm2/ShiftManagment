package com.example.shiftmanagment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;
import com.example.shiftmanagment.view.MainActivity;

public class MainActivityViewModel extends ViewModel {

    private  Database database = Database.getInstance();

    public void signInUser(final String username,final String password , final MainActivity.LogInActions logInActions){
        database.signInUser(username, password, logInActions);
    }

}
