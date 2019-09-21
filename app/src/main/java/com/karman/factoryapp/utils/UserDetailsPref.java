package com.karman.factoryapp.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class UserDetailsPref {
    public static String USER_NAME = "user_name";
    public static String USER_MOBILE = "user_mobile";
    public static String USER_EMAIL = "user_email";
    public static String USER_IMAGE = "user_image";
    public static String USER_TYPE = "user_type";
    public static String USER_TOKEN = "user_token";
    public static String FIREBASE = "firebase";

    private static UserDetailsPref appDetailsPref;
    private String USER_DETAILS = "USER_DETAILS";


    public static UserDetailsPref getInstance() {
        if (appDetailsPref == null)
            appDetailsPref = new UserDetailsPref();
        return appDetailsPref;
    }

    private SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(USER_DETAILS, Context.MODE_PRIVATE);
    }

    public String getStringPref(Context context, String key) {
        return getPref(context).getString(key, "");
    }

    public int getIntPref(Context context, String key) {
        return getPref(context).getInt(key, 0);
    }

    public boolean getBooleanPref(Context context, String key) {
        return getPref(context).getBoolean(key, false);
    }

    public void putBooleanPref(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putStringPref(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putIntPref(Context context, String key, int value) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }
}
