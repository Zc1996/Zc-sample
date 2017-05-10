package com.microsoft.projectoxford.emotionsample.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zc on 2017/5/10.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_TABLE_USER="create table user (" +
            "id integer primary key autoincrement," +
            "username text," +
            "password text," +
            "sex integer," +
            "age integer," +
            "date text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
