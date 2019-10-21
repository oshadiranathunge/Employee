package com.example.empo.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class EmployeeDetails extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "UserInfo.db";

        public EmployeeDetails(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    public EmployeeDetails(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + EmployeeInfo.Employee.TABLE_NAME + " (" +
                        EmployeeInfo.Employee._ID + " INTEGER PRIMARY KEY," +
                        EmployeeInfo.Employee.COLUMN_1 + " TEXT," +
                        EmployeeInfo.Employee.COLUMN_2 + " TEXT," +
                        EmployeeInfo.Employee.COLUMN_3 + " TEXT," +
                        EmployeeInfo.Employee.COLUMN_4 + " TEXT)";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + EmployeeInfo.Employee.TABLE_NAME;

        public long addInfo(String userName, String telephone, String gender, String type){
            // Gets the data repository in write mode
            SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(EmployeeInfo.Employee.COLUMN_1, userName);
            values.put(EmployeeInfo.Employee.COLUMN_2, telephone);
            values.put(EmployeeInfo.Employee.COLUMN_3,gender );
            values.put(EmployeeInfo.Employee.COLUMN_4, type);

// Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(EmployeeInfo.Employee.TABLE_NAME, null, values);

            return newRowId;

        }
        public boolean updateInfor(String userName, String telephone, String gender, String type){
            SQLiteDatabase db = getWritableDatabase();

// New value for one column

            ContentValues values = new ContentValues();
            values.put(EmployeeInfo.Employee.COLUMN_2,telephone );
            values.put(EmployeeInfo.Employee.COLUMN_3,gender );
            values.put(EmployeeInfo.Employee.COLUMN_4,type );


// Which row to update, based on the title
            String selection = EmployeeInfo.Employee.COLUMN_1 + " LIKE ?";
            String[] selectionArgs = { userName };

            int count = db.update(
                    EmployeeInfo.Employee.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);

            if (count>0){
                return true;
            }else{
                return false;
            }

        }
        public List readAllInfor(){
            SQLiteDatabase db = getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    BaseColumns._ID,
                   EmployeeInfo.Employee.COLUMN_1,
                    EmployeeInfo.Employee.COLUMN_2,
                    EmployeeInfo.Employee.COLUMN_3,
                    EmployeeInfo.Employee.COLUMN_4,

            };

            // Filter results WHERE "title" = 'My Title'
            String selection = EmployeeInfo.Employee.COLUMN_1 + " = ?";
            String userName = "ABc";
            String[] selectionArgs = { userName };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    EmployeeInfo.Employee.COLUMN_1 + " ASC";

            Cursor cursor = db.query(
                    EmployeeInfo.Employee.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );
            List users = new ArrayList<>();
            while(cursor.moveToNext()) {
                String usersID = cursor.getString(
                        cursor.getColumnIndexOrThrow(EmployeeInfo.Employee.COLUMN_1));
                users.add(usersID);
            }
            cursor.close();
            return users;


        }

        public List readAllInfor(String userName){
            SQLiteDatabase db = getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    BaseColumns._ID,
                    EmployeeInfo.Employee.COLUMN_1,
                    EmployeeInfo.Employee.COLUMN_2,
                    EmployeeInfo.Employee.COLUMN_3,
                    EmployeeInfo.Employee.COLUMN_4,

            };

            // Filter results WHERE "title" = 'My Title'
            String selection = EmployeeInfo.Employee.COLUMN_1 + " LIKE ?";
            String[] selectionArgs = { userName };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                   EmployeeInfo.Employee.COLUMN_1 + " ASC";

            Cursor cursor = db.query(
                    EmployeeInfo.Employee.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,// ,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );
            List usersINfo = new ArrayList<>();
            while(cursor.moveToNext()) {
                String usersname = cursor.getString(cursor.getColumnIndexOrThrow(EmployeeInfo.Employee.COLUMN_1));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(EmployeeInfo.Employee.COLUMN_3));
                String telephone = cursor.getString(cursor.getColumnIndexOrThrow(EmployeeInfo.Employee.COLUMN_2));
                String gender = cursor.getString(cursor.getColumnIndexOrThrow(EmployeeInfo.Employee.COLUMN_4));
                usersINfo.add(usersname);
                usersINfo.add(type);
                usersINfo.add(telephone);
                usersINfo.add(gender);

            }
            cursor.close();
            return usersINfo;


        }
        public void deleteInfo(String username){

            SQLiteDatabase db = getReadableDatabase();

            // Define 'where' part of query.
            String selection = EmployeeInfo.Employee.COLUMN_1 + " LIKE ?";
// Specify arguments in placeholder order.
            String[] selectionArgs = { username };
// Issue SQL statement.
            int deletedRows = db.delete(EmployeeInfo.Employee.TABLE_NAME, selection, selectionArgs);

        }




    }



