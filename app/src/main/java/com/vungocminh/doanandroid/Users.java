package com.vungocminh.doanandroid;

public class Users {

    String address, fullName, phoneNumber, password;

    public Users() {
    }

    public Users(String address, String fullName, String phoneNumber, String password) {
        this.address = address;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    // getters and setters

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}