package com.c2.studentmanagementsystem.session;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    Context context;

    public Session(Context context){
        this.context = context;
        preferences = context.getSharedPreferences("register", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.apply();
    }

    public void setLoggedIn(boolean loggedIn){
        editor.putBoolean("loggedInMode", loggedIn);
        editor.apply();
    }

    public boolean logIn(){
        return preferences.getBoolean("loggedInMode", false);
    }

}
