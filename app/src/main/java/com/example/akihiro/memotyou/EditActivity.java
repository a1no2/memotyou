package com.example.akihiro.memotyou;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    boolean create;             //DBにメモを新しく追加するか上書きするか
    EditText title_Edittext;
    EditText content_Edittext;

    static SQLiteDatabase mydb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar_edit));      //アクション

        MyDB hlpr = new MyDB(getApplicationContext());
        mydb = hlpr.getWritableDatabase();      //w読み書き R読み込み

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //とりあえず今は新規保存だけ
    //あとでif文かませたらcreateかUPdateの分岐
    public void a(View v){
        switch (v.getId()){
            case R.id.a:
                title_Edittext = (EditText)findViewById(R.id.title_Edittext);
                content_Edittext = (EditText)findViewById(R.id.content_Edittext);
                String title_str = title_Edittext.getText().toString();
                String content_str = content_Edittext.getText().toString();

                String insert_SQL = "insert into mydata (title,content)"
                                    + "values ('"+ title_str + "','" + content_str + "' )";

                mydb.execSQL(insert_SQL);




        }
    }

}
