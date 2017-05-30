package com.shivshankar.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shivshankar on 03-01-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="student.db";
    private static final String DATABASE_TABLE="student";
    private static final String KEY_ID="Id";
    private static final String KEY_NAME="Name";
    private static final String KEY_EMAIL="Email";
    private static final String DATABASE_TABLE_CREATE="create table " + DATABASE_TABLE
            +" (" +KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +KEY_NAME+ " TEXT,"
            +KEY_EMAIL+" TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exits " +DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(String name,String email)
    {
        sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_EMAIL,email);
        long result=sqLiteDatabase.insert(DATABASE_TABLE,null,cv);
        if (result==-1)
        {
            return true;
        }
        else
        return false;
    }

    public Cursor getAllData()
    {
        sqLiteDatabase=this.getReadableDatabase();
        Cursor result=sqLiteDatabase.rawQuery("select * from "+DATABASE_TABLE,null);
        return result;
    }

    public boolean update(String id,String name,String email)
    {
        if (id.isEmpty()&&name.isEmpty()&&email.isEmpty())
        {return false;}
        else {
        sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_ID,id);
        cv.put(KEY_NAME,name);
        cv.put(KEY_EMAIL,email);
        sqLiteDatabase.update(DATABASE_TABLE,cv,KEY_ID+"=?", new String[] {id});
        return true;}
    }

    public int deleteData(String id)
    {
        sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(DATABASE_TABLE,KEY_ID+"=?",new String[]{String.valueOf(id)});
    }
}
