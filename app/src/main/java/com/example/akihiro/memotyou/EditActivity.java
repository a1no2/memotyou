package com.example.akihiro.memotyou;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class EditActivity extends AppCompatActivity implements View.OnClickListener{
    static SQLiteDatabase mydb;
    static Cursor c;
    static boolean create_boolean;          //DBにメモを新しく追加するか上書きするか
    static int save_id;                    //保存する場合どのIDに保存するか
    static EditText title_Edittext;
    static EditText maintext_Edittext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_edit));      //ツールバー

        //遷移
        Intent intent =getIntent();
        create_boolean = intent.getBooleanExtra("create_boolean", true);
        save_id = (intent.getIntExtra("saveID",-1));

        
        //DB
        MyDB DB_hlpr = new MyDB(getApplicationContext());
        mydb = DB_hlpr.getWritableDatabase();       //w読み書き R読み込み

        //XMLと紐づけとかonClikc
        Button save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(this);
        title_Edittext = (EditText)findViewById(R.id.title_Edittext);
        maintext_Edittext = (EditText)findViewById(R.id.maintext_Edittext);

        String aa ="取ってきたIDの確認　　";
        c = mydb.query(
                MyDB.TABLE_NAME,
                new String[]{MyDB.memo_ID,MyDB.memo_title,MyDB.memo_mainText},
                MyDB.memo_ID + " = ?",new String[] {""+String.valueOf(save_id)},
                null,null,null,null
        );
        while (c.moveToNext()){
            title_Edittext.setText(c.getString(c.getColumnIndexOrThrow(MyDB.memo_title)));
            maintext_Edittext.setText(c.getString(c.getColumnIndexOrThrow(MyDB.memo_mainText)));
        }





    }


    //つーるばー
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //とりあえず今は新規保存だけ
    public void onClick(View v){
        if(v != null){
            switch (v.getId()){
                case R.id.save_btn:
                    title_Edittext = (EditText)findViewById(R.id.title_Edittext);
                    maintext_Edittext = (EditText)findViewById(R.id.maintext_Edittext);
                    String title_str = title_Edittext.getText().toString();
                    String maintext_str = maintext_Edittext.getText().toString();
                    String SQL_str = "";
                    if (create_boolean){
                        SQL_str =   "insert into "
                                + MyDB.TABLE_NAME + " (" + MyDB.memo_title + "," + MyDB.memo_mainText + ")"
                                + " values ('"+ title_str + "','" + maintext_str + "' );";
                            create_boolean = false;
                        try {
                            mydb.execSQL(SQL_str);
                        }catch (SQLException e) {
                        }
                    }else {
                        SQL_str = "update " + MyDB.TABLE_NAME + " set "
                                + MyDB.memo_title + " = '" + title_str + "',"
                                + MyDB.memo_mainText + " = '" + maintext_str + "'"
                                + " where " + MyDB.memo_ID + " = '" + String.valueOf(save_id) + "';";
                        try {
                            mydb.execSQL(SQL_str);
                        }catch (SQLException e){
                        }
                    }



            }
        }
    }



}
