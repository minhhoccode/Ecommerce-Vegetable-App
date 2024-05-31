package com.vungocminh.doanandroid;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "LoginSession";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";

    public SessionManager(Context context) {
        int PRIVATE_MODE = 0;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String phoneNumber, String password, String name, String address) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_PHONE_NUMBER, phoneNumber);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_ADDRESS, address);
        editor.commit();
    }

    public boolean checkLogin() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    public String getUserDetails(String key) {
        return sharedPreferences.getString(key, null);
    }

    public void setLoginStatus(boolean status) {
        editor.putBoolean(IS_LOGIN, status);
        editor.commit();
    }

    public boolean getLoginStatus() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }
    public void updateAddress(String address) {
        editor.putString(KEY_ADDRESS, address);
        editor.commit();
    }
}