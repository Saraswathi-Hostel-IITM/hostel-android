package com.sarasapp.sarasapp.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.sarasapp.sarasapp.Helper.DatabaseHelper;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kevinselvaprasanna on 4/2/16.
 */
public class Photo {
    public static String imgurl,id;
    public static final String TABLE_NAME = "Gallery";
    public static String[] columns = {"imgurl","id"};

    public Photo(String imgurl,String id) {
        this.imgurl = imgurl;
        this.id = id;
    }

    public Photo(JSONObject jphoto) {

    }

    public static ArrayList<Photo> getArrayList(Cursor c) {
        ArrayList<Photo> arrayList = new ArrayList<>();
        while (c.moveToNext()) {
            arrayList.add(parseNot(c));
        }
        return arrayList;
    }

    public static Photo parseNot(Cursor c) {
        Photo pet = new Photo(c.getString(0),c.getString(1));
        return pet;
    }

    public static ArrayList<Photo> getAllPhotos(Context context){
        DatabaseHelper data = new DatabaseHelper(context);
        return data.getAllPhotos();
    }
    public static void savePhoto(Context mContext){
        ContentValues cv = new ContentValues();
        cv.put("imgurl",imgurl);
        cv.put("id",id);
        DatabaseHelper dh = new DatabaseHelper(mContext);
        dh.addPhoto(cv);
    }

}
