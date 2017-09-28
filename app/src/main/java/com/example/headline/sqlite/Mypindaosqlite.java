package com.example.headline.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class Mypindaosqlite extends SQLiteOpenHelper {
    public Mypindaosqlite(Context context) {
        super(context, "item.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table item(id integer primary key autoincrement,title text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
