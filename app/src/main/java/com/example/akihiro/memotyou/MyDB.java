package com.example.akihiro.memotyou;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper{
    private static final String DB_name = "mydata.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "mymemodata";

    public static final String memo_ID = "_id";             //ID PK オートインクリメント
    public static final String memo_title  = "title";       //メモ帳の件名、タイトル
    public static final String memo_mainText = "maintext";  //メモ帳の本文、内容

    String TABLEcreateSQL = "create table " + TABLE_NAME + "("
            + memo_ID + " integer primary key autoincrement, "
            + memo_title + " text, "
            + memo_mainText + " text)";


    public MyDB(Context context){
        super(context,DB_name,null,DB_VERSION);
    }

    //DBなかったら呼ばれる?
    //DBはあるらしいからテーブルの作成からやる
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLEcreateSQL);
    }

    //コンストラクタと参照先のDBに差があったら呼ばれる
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}