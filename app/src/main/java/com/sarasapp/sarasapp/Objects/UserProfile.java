package com.sarasapp.sarasapp.Objects;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;


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
    public static void setPhone(String phone,Context context){
        SharedPreferences preferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone",phone);
        editor.commit();
    }

    public static void setUserid(String id,Context context){
        SharedPreferences preferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userid",id);
        editor.commit();
    }
    public static void setRoll(String roll,Context context){
        SharedPreferences preferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("roll",roll);
        editor.commit();
    }
    public static void setPassword(String roll,Context context){
        SharedPreferences preferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("pass",roll);
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
    public static String getPhone(Context context) {
        SharedPreferences pref = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        return pref.getString("phone", "");
    }

    public static String getToken(Context context) {
        SharedPreferences pref = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        return pref.getString("token", "");
    }
    public static String getUserid(Context context) {
        SharedPreferences pref = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        return pref.getString("userid", "");
    }
    public static String getRoll(Context context) {
        SharedPreferences pref = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        return pref.getString("roll", "");
    }
    public static String getPassword(Context context) {
        SharedPreferences pref = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        return pref.getString("pass", "");
    }



}


