package com.example.shiftmanagment.util;

public class User {

    private String firstName;
    private String lastName;
    private int salary;
    private boolean Manager;

    public User() {}

    public User(String firstName, String lastName, int salary, boolean Manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.Manager = Manager;
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

    public int getSalary() {
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
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", isManager=" + Manager +
                '}';
    }
}
