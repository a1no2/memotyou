package com.example.akihiro.memotyou;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper{
    private static final String DB_name = "mydata.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "mydata";

    public final String ID = "_id";             //ID　PK　おーといんくりめんと
    public final String title  = "title";       //メモ帳の件名、タイトル
    public final String content = "content";    //メモ帳の本文、内容

    String TABLEcreateSQL = "create table " + TABLE_NAME + "("
            + ID + " integer auto_increment primary key, "
            + title + " text, "
            + content + " text)";


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