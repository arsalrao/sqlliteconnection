package com.boost.entertainment.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by waqas on 19/09/2016.
 */
public class ContactDataSource {

    private DBHelper dbHelper ;

    private SQLiteDatabase sqLiteDatabase ;

    private String[] allColumns = new String[]{DBHelper.ID , DBHelper.NAME , DBHelper.EMAIL , DBHelper.ADDRESS};


    private Context context ;

    public ContactDataSource (Context context){
        dbHelper = new DBHelper(context);
        this.context = context ;
    }

    public void openToWrite() throws SQLException{
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public void openToRead() throws SQLException{
        sqLiteDatabase = dbHelper.getReadableDatabase();
    }

    public void close(){
        sqLiteDatabase.close();
    }

    public boolean insertContact(Contact contact){
        openToWrite();
        ContentValues contentValues = new ContentValues();

        try{

            contentValues.put(DBHelper.NAME , contact.getName());
            contentValues.put(DBHelper.EMAIL , contact.getEmail());
            contentValues.put(DBHelper.ADDRESS , contact.getAddress());

            sqLiteDatabase.insert(DBHelper.TABLE , null , contentValues);
            return true ;
        }catch (Exception e){
            e.printStackTrace();
            return false ;
        }finally {
            close();
        }


    }

    public Contact getContactById(int id){
        openToRead();

        Contact contact = null;

        try{
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DBHelper.TABLE+" WHERE "+DBHelper.ID+" = "+id , null);
            cursor.moveToFirst();

            String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
            String email = cursor.getString(cursor.getColumnIndex(DBHelper.EMAIL));
            String address = cursor.getString(cursor.getColumnIndex(DBHelper.ADDRESS));

            contact = new Contact(name , email , address);
            return  contact ;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
        return  contact ;
    }


    public ArrayList<Contact> getAllContacts(){

       openToRead();

        ArrayList<Contact> arrayList = new ArrayList<>();
        try {

            Cursor cursor =sqLiteDatabase.rawQuery("SELECT * FROM "+DBHelper.TABLE ,null);

            cursor.moveToFirst();



            while (!cursor.isAfterLast()) {
                Contact contact = new Contact();

                contact.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.ID)));

                contact.setAddress(cursor.getString(cursor.getColumnIndex(DBHelper.ADDRESS)));
                contact.setName(cursor.getString(cursor.getColumnIndex(DBHelper.NAME)));
                contact.setEmail(cursor.getString(cursor.getColumnIndex(DBHelper.EMAIL)));

                arrayList.add(contact);

                cursor.moveToNext();


            }
            return arrayList ;
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null ;
        }finally {
            close();
        }


    }

    public boolean deleteContactById(int id ){
        openToWrite();

        try {
            sqLiteDatabase.delete(DBHelper.TABLE, "id = ?", new String[]{id + ""});
            return true ;
        }catch (Exception e){
            Toast.makeText(context, "error to delete"+e.getMessage(), Toast.LENGTH_SHORT).show();
            return false ;
        }finally {
            close();
        }
    }

}
