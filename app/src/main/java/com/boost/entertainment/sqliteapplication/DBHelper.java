package com.boost.entertainment.sqliteapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by waqas on 18/09/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "my_db";
    public static final String TABLE = "my_contacts";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String ID = "id";


    Context context ;


    public DBHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.context = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {

            Log.i("created"," before create table");
            final String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, "
                    + EMAIL + " TEXT, " +ADDRESS + " TEXT" + ")";

            //sqLiteDatabase.execSQL("create table if not exits " + TABLE + "("+ID+" integer primary key," + NAME + " text ," + EMAIL + " text ," + ADDRESS + " text)");

            sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
            Log.i("created","after create table");

        }catch (Exception e)
        {
            e.printStackTrace();

            Toast.makeText(context,"onCreate "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        onCreate(sqLiteDatabase);

    }


}
