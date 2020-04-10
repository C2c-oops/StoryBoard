package com.c2.studentmanagementsystem.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbRegisterHelper extends SQLiteOpenHelper {

    private static final String TAG = DbRegisterHelper.class.getSimpleName();

    private static final String DB_NAME = "register.db";
    private static final int DB_VERSION = 1;

    //Teachers
    private static final String TEACHER_TABLE = "teachers";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_REG = "regid";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_PASS = "password";

    //Students
    private static final String STUDENTS_TABLE = "students";
    private static final String COLUMN_SID = "__id";
    private static final String COLUMN_SREG = "sregNo";
    private static final String COLUMN_SNAME = "sname";
    private static final String COLUMN_SSECTION = "ssection";
    private static final String COLUMN_SEMAIL = "semail";
    private static final String COLUMN_SPHONE = "sphone";
    private static final String COLUMN_SFEES = "sphone";


    public DbRegisterHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static final String CREATE_TABLE_TEACHERS = "CREATE TABLE " + TEACHER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_REG + " INTEGER,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PHONE + " INTEGER,"
            + COLUMN_PASS + " TEXT);";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE " + STUDENTS_TABLE + "("
            + COLUMN_SID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SREG + " INTEGER,"
            + COLUMN_SNAME + " TEXT,"
            + COLUMN_SSECTION + " TEXT,"
            + COLUMN_SEMAIL + " TEXT,"
            + COLUMN_SPHONE + " INTEGER,"
            + COLUMN_SFEES + " INTEGER);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TEACHERS);
        db.execSQL(CREATE_TABLE_STUDENTS);
        Log.d(TAG, "onCreate: DataBase Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TEACHER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE);
        onCreate(db);
    }

    public void addTeacher(String name, Integer regid, String email, Integer phone, String password){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_REG, regid);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_PASS, password);

        long id = database.insert(TEACHER_TABLE, null, values);
        database.close();

        Log.d(TAG, "User Added" + id);
    }

    public boolean getTeacher(Integer regid, String password){
        String selectQuery = "select * from " + TEACHER_TABLE + " where " +
                COLUMN_REG + " = " + "'" + regid + "'" + " and " +
                COLUMN_PASS + " = " + "'" + password + "'";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            return true;
        }
        cursor.close();
        database.close();

        return false;
    }

    public void addStudents(Integer sregno, String sname, String ssection, String semail, Integer sphone, Integer sfees){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SREG, sregno);
        values.put(COLUMN_SNAME, sname);
        values.put(COLUMN_SSECTION, ssection);
        values.put(COLUMN_SEMAIL, semail);
        values.put(COLUMN_SPHONE, sphone);
        values.put(COLUMN_SFEES, sfees);

        long sid = database.insert(STUDENTS_TABLE, null, values);
        database.close();

        Log.d(TAG, "User Added" + sid);
    }

    public ArrayList<String> showStudents(){
        SQLiteDatabase db = getReadableDatabase();
        String query="select * from "+STUDENTS_TABLE;
        Cursor cr = db.rawQuery(query,null);

        ArrayList<String> al = new ArrayList<>();
        while(cr.moveToNext()) {
            int sregno = cr.getInt(0);
            String sname = cr.getString(1);
            String ssection = cr.getString(2);
            String semail = cr.getString(3);
            int sphone = cr.getInt(4);
            int sfees = cr.getInt(5);

            String s6 = "RegId is: " + sregno +
                    "\nName is: " + sname + "" +
                    "\nSection is: " + ssection + "" +
                    "\nEmail is: " + semail + "" +
                    "\nPhone No. is: " + sphone + "" +
                    "\nFees is: " + sfees;
            al.add(s6);
        }
        return al;
    }

    public void deleteStudents(String s){
        SQLiteDatabase db=getWritableDatabase();
        String where="sname =?";
        String [] ss={s};
        db.delete(STUDENTS_TABLE,where,ss);
    }

    public void updateStudents(String s, String se, Integer p, Integer f){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("ssection",se);
        cv.put("sphone", p);
        cv.put("sfees",f);
        String where="sname=?";
        String []ss={s};
        db.update(STUDENTS_TABLE,cv,where,ss);
    }

    public ArrayList<String> searchStudents(String s){
        SQLiteDatabase db=getReadableDatabase();

        String query="select * from " + STUDENTS_TABLE + " where sname=?";
        String []ss={s};
        Cursor cr = db.rawQuery(query,ss);

        ArrayList<String> al=new ArrayList<>();
        while(cr.moveToNext()) {
            int sregno = cr.getInt(0);
            String sname = cr.getString(1);
            String ssection = cr.getString(2);
            String semail = cr.getString(3);
            int sphone = cr.getInt(4);
            int sfees = cr.getInt(5);

            String s6 = "RegId is: " + sregno +
                    "\nName is: " + sname + "" +
                    "\nSection is: " + ssection + "" +
                    "\nEmail is: " + semail + "" +
                    "\nPhone No. is: " + sphone + "" +
                    "\nFees is: " + sfees;
            al.add(s6);
        }
        return al;
    }
}
