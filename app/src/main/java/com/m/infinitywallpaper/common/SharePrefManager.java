package com.m.infinitywallpaper.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefManager {


    static SharePrefManager _instance;

    Context context;
    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedPrefEditor;


    final String isLogin = "isLogin__";

    public static SharePrefManager instance(Context context) {
        if (_instance == null) {
            _instance = new SharePrefManager();
            _instance.configSessionUtils(context);
        }
        return _instance;
    }

    public static SharePrefManager instance() {
        return _instance;
    }

    public void configSessionUtils(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences("AppPreferences", Activity.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
    }

    public void storeValueString(String key, String value) {
        sharedPrefEditor.putString(key, value);
        sharedPrefEditor.commit();
    }

    public String fetchValueString(String key) {
        return sharedPref.getString(key, null);
    }

    // check login status

    public boolean isLogin(){
        return sharedPref.getBoolean(isLogin,false);
    }

    // set already login
    public void setIsLogin(){
        sharedPrefEditor.putBoolean(isLogin,true);
        sharedPrefEditor.commit();
    }


    // set status that is "log out"
    public void setIsLogout(){
        sharedPrefEditor.putBoolean(isLogin,false);
    }
}
