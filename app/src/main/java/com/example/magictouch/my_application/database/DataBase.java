package com.example.magictouch.my_application.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.magictouch.my_application.Model.UserModel;
import com.example.magictouch.my_application.ModelPayment;

import java.util.ArrayList;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DataBase extends SQLiteOpenHelper {
    //  name of database
    public static final String DATABASE_NAME = "smo.db";
    private static final int DATABASE_VERSION = 9;

    // columns of the user table
    public static final String TABLE_USER = "user";
    public static final String ID_USER = "id_user";
    public static final String First_Name = "first_name";
    public static final String Last_Name = "last_name";
    public static final String Family_Numb = "family_number";
    public static final String Address = "adress";
    public static final String E_mail = "e_mail";
    public static final String Password = "password";


    //  columns of the notification
    public static final String TABLE_NOTIFIDATIONS = "notification";
    public static final String ID_NOTIFICATION= "id_notification";
    public static final String Date = "date";
    public static final String Type = "type";
    public static final String User_not_ID = "user_not_id";


    //  columns of the tarification
    public static final String TABLE_TARIFICAION = "tarification";
    public static final String ID_TARIFICATION = "id_tarification";
    public static final String Cost = "cost";
    public static final String Payment_Date = "payment_date";
    public static final String Deadline = "deadline";
    public static final String User_tar_ID = "user_tar_id";
    public static final String Consumption_tar_ID = "consumption_tar_id";

    //  columns of consumption
    public static final String TABLE_CONSUMPTION = "consumption";
    public static final String ID_CONSUMPTION = "id_consumption";
    public static final String Date_Consumption = "date_consumption";
    public static final String Value_Consumption = "value_consumption";
    public static final String User_cons_ID = "user_cons_id";
    public static final String Tarification_cons_ID = "tarification_cons_id";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USER + " ("
                + ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + First_Name + " TEXT NOT NULL, "
                + Last_Name + " TEXT NOT NULL, "
                + Address + " TEXT NOT NULL, "
                + Family_Numb + " INTEGER NOT NULL,"
                + E_mail + " TEXT NOT NULL,"
                + Password + " TEXT NOT NULL "
                + ");");

        db.execSQL("CREATE TABLE " + TABLE_NOTIFIDATIONS + " ("
                + ID_NOTIFICATION + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Date + " TEXT NOT NULL, "
                + Type + " TEXT NOT NULL, "
                + User_not_ID + " INTEGER NOT NULL , "
                + " FOREIGN KEY ( " + User_not_ID + " ) REFERENCES " + TABLE_USER + " ( " + ID_USER + " ) "
                + ");");

        db.execSQL("CREATE TABLE " + TABLE_TARIFICAION + " ("
                + ID_TARIFICATION + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Cost + " REAL NOT NULL, "
                + Payment_Date + " TEXT NOT NULL, "
                + Deadline + " TEXT NOT NULL "
                + " );");

        db.execSQL("CREATE TABLE " + TABLE_CONSUMPTION + " ("
                + ID_CONSUMPTION + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Date_Consumption + " TEXT NOT NULL, "
                + Value_Consumption + " REAL NOT NULL "
                + " );");


        // db.execSQL("create table " + TABLE_USER + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFIDATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TARIFICAION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONSUMPTION);

        onCreate(db);
    }

    public boolean insertUser (String First_Nam, String Last_Nam , int Familly_Numer,String Addres, String E_mai, String Passw ) {

       Log.e("email",E_mai);
       Log.e("email",First_Nam);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(First_Name, First_Nam );
        contentValues.put(Last_Name, Last_Nam);
        contentValues.put(Family_Numb, Familly_Numer);
        contentValues.put(Address, Addres);
        contentValues.put(E_mail, E_mai);
        contentValues.put(Password, Passw);

        long result = db.insert(TABLE_USER, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }


     public boolean insertNotification (String date,String type,int idUser ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Date, date );
        contentValues.put(Type, type);
        contentValues.put(User_not_ID,idUser);

        long result = db.insert(TABLE_NOTIFIDATIONS, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }



    public boolean insertTarification (float cost,  String payment_Date , String deadline ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cost, cost  );
        contentValues.put(Payment_Date, payment_Date);
        contentValues.put(Deadline, deadline  );

        long result = db.insert(TABLE_TARIFICAION, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean insertConsumption (String date_consumption,  float value_Consumption  ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Date_Consumption , date_consumption );
        contentValues.put(Value_Consumption, value_Consumption);

        long result = db.insert(TABLE_CONSUMPTION, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public ArrayList<Float> getValueConsumption() {

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Float> list=new ArrayList<>();
        Cursor res = db.rawQuery("select * from " + TABLE_CONSUMPTION, null);

        res.moveToNext();
        do {
            list.add(res.getFloat(res.getColumnIndex(Value_Consumption)));
        }while (res.moveToNext());
        Log.e( "getValueConsumption: ",list.size()+"" );
        return list;
    }



    public void getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_USER, null);
        Log.e("sssss","sssssssssssss"+res.getCount());
        //return res;
    }


    // TODO: 14/06/2018 don't foget to get the id of user when you call this method  ^^
    public boolean Update_user(String id_user , String First_Nam, String Last_Nam, int Familly_Numer, String Addres, String E_mai, String Passw) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(First_Name, First_Nam);
        contentValues.put(Last_Name, Last_Nam);
        contentValues.put(Family_Numb, Familly_Numer);
        contentValues.put(Address, Addres);
        contentValues.put(E_mail, E_mai);
        contentValues.put(Password,Passw);
        db.update(TABLE_USER, contentValues, "ID = ?", new String[]{id_user});
        return true;
    }

    // TODO: 14/06/2018 same here het the id of notification and delete it ^^
    public Integer Delete_notification (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NOTIFIDATIONS, "ID_USER = ?", new String[]{id});
    }



    public UserModel getUserByEmail(String email,String password) {

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_USER, null);
        res.moveToFirst();
        do {
            Log.e("pppp",res.getString(res.getColumnIndex(E_mail)));
            if (res.getString(res.getColumnIndex(E_mail)).equals(email) &&
                    res.getString(res.getColumnIndex(Password)).equals(password)) {
                UserModel userModel=new UserModel();
                userModel.setId(res.getInt(res.getColumnIndex(ID_USER)));
                userModel.setEmail(res.getString(res.getColumnIndex(E_mail)));
                return userModel;
            }

        }while (res.moveToNext());

         return null;
    }


    public ArrayList<ModelPayment> searchByDate(String date){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cusror;
        ArrayList<ModelPayment> list=new ArrayList<>();

        cusror =db.rawQuery("SELECT * FROM "+ TABLE_TARIFICAION + " WHERE " +
                Payment_Date +"="+ date,null);

        cusror.moveToFirst();
        do {
            list.add(new ModelPayment(1,"1520","2018/10/10","2018/12/31"));
        }while (cusror.moveToNext());

        return list;
    }


}