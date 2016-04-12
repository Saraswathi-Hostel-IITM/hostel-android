package com.sarasapp.sarasapp.Objects;

import android.content.Context;
import android.content.SharedPreferences;


public class UserProfile {

    public static String name;

    public static void setName(String name, Context context){
        SharedPreferences preferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name",name);
        editor.commit();
    }
    public static void setEmail(String email,Context context){
        SharedPreferences preferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email",email);
        editor.commit();
    }
    public static void setToken(String token, Context context){
        SharedPreferences preferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.commit();
    }
    public static String getName(Context context) {
        SharedPreferences pref = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        return pref.getString("name","");
    }
    public static String getEmail(Context context) {
        SharedPreferences pref = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        return pref.getString("email", "");
    }

    public static String getToken(Context context) {
        SharedPreferences pref = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        return pref.getString("token", "");
    }




}


