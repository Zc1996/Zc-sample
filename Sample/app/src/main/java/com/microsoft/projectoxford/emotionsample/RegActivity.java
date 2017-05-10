package com.microsoft.projectoxford.emotionsample;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.microsoft.projectoxford.emotionsample.helper.MyDatabaseHelper;

/**
 * Created by Zc on 2017/5/10.
 */

public class RegActivity extends ActionBarActivity {

    private EditText reg_account;
    private EditText reg_password;
    private EditText reg_confirm;
    private EditText reg_age;
    private RadioButton reg_radioMale;
    private Button submit;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper=new MyDatabaseHelper(this,"user.db",null,1);
        reg_account=(EditText)findViewById(R.id.reg_account);
        reg_password=(EditText)findViewById(R.id.reg_password);
        reg_confirm=(EditText)findViewById(R.id.reg_confirm);
        reg_age=(EditText)findViewById(R.id.reg_age);
        reg_radioMale=(RadioButton)findViewById(R.id.reg_radioMale);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=reg_account.getText().toString();
                String password=reg_password.getText().toString();
                Boolean confirm=password.equals(reg_confirm.getText().toString());
                String age=reg_age.getText().toString();
                Boolean isMale=reg_radioMale.isChecked();
                if(confirm){
                    SQLiteDatabase db=dbHelper.getWritableDatabase();
                    Cursor cursor=db.rawQuery("select count(*) from user where username =?",new String[]{username});
                    cursor.moveToFirst();
                    if(cursor.getInt(0)>0){
                        cursor.close();
                        Toast.makeText(RegActivity.this,"该用户名已经存在",Toast.LENGTH_SHORT).show();
                    }else {
                        cursor.close();
                        ContentValues values=new ContentValues();
                        values.put("username",username);
                        values.put("password",password);
                        values.put("sex",isMale?1:0);
                        values.put("age",Integer.parseInt(age));
                        values.put("date","");
                        db.insert("user",null,values);
                        Intent intent=new Intent(RegActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    db.close();
                }else{
                    Toast.makeText(RegActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
