package com.example.akihiro.memotyou;

import android.content.Intent;
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
    static boolean create_boolean;             //DBにメモを新しく追加するか上書きするか
    EditText title_Edittext;
    EditText content_Edittext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_edit));      //ツールバー

        //遷移
        Intent intent =getIntent();
        create_boolean = intent.getBooleanExtra("create_boolean", true);

        //DB
        MyDB DB_hlpr = new MyDB(getApplicationContext());
        mydb = DB_hlpr.getWritableDatabase();       //w読み書き R読み込み

        //XMLと紐づけとかonClikc
        Button save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(this);


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
                    content_Edittext = (EditText)findViewById(R.id.content_Edittext);
                    String title_str = title_Edittext.getText().toString();
                    String content_str = content_Edittext.getText().toString();
                    String SQL="";
                    if (create_boolean){
                        SQL =   "insert into " + MyDB.TABLE_NAME
                                + " (" + MyDB.title + "," + MyDB.content + ")"
                                + " values ('"+ title_str + "','" + content_str + "' )";
                        create_boolean = false;
                    }else {

                    }


                    try {
                        mydb.execSQL(SQL);
                    }catch (SQLException e) {
                    }
            }
        }
    }



}
