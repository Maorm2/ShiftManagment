package com.example.shiftmanagment.util;

import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;

public class Employee {

    private  String email;
    private String password;
    private String firstName;
    private String lastName;
    private double salary;
    private boolean Manager;
//    private HashMap<String, String> currentWeekShifts;
//    private HashMap<String, String> nextWeekShifts;


    public Employee() {}

    public Employee(String email,String password,String firstName, String lastName, double salary, boolean Manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.Manager = Manager;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isManager() {
        return Manager;
    }

    public void setManager(boolean manager) {
        Manager = manager;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", Manager=" + Manager +
                '}';
    }
}
