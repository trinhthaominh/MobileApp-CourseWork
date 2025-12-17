package com.example.bai22_1sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME , null, DATABASE_VERSION ) {
    companion object {
        private const val DATABASE_NAME = "USERDB"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(p0 : SQLiteDatabase?) {
        // Tạo bảng
        p0?.execSQL("CREATE TABLE USERS (\n" +
                "USERID   INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                     UNIQUE\n" +
                "                     NOT NULL,\n" +
                "USERNAME TEXT,\n" +
                "PWD      TEXT\n" +
                ");\n")

        // Add data
        p0?.execSQL("INSERT INTO USERS (USERNAME, PWD) VALUES ('tuhoc.cc@gmail.com', '12345')")
        p0?.execSQL("INSERT INTO USERS (USERNAME, PWD) VALUES ('teo@gmail.com', '54321')")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {

    }
}