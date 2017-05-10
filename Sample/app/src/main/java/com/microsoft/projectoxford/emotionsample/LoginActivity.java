package com.microsoft.projectoxford.emotionsample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.projectoxford.emotionsample.helper.MyDatabaseHelper;

/**
 * Created by Zc on 2017/4/26.
 */

public class LoginActivity extends ActionBarActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private Button reg;
    private CheckBox remeberPass;
    private MyDatabaseHelper dbHelper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper=new MyDatabaseHelper(this,"user.db",null,1);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit =(EditText) findViewById(R.id.account);
        passwordEdit=(EditText)findViewById(R.id.password);
        remeberPass = (CheckBox)findViewById(R.id.remeber_pass);
        login = (Button) findViewById(R.id.login);
        reg=(Button)findViewById(R.id.reg);
        boolean  isRemeber =pref.getBoolean("remeber_password",false);
        if (isRemeber){
            //将账号和密码都设置在文本框中
            String account = pref.getString("account","");
            String password =pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            remeberPass.setChecked(true);
        }
        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                //如果账号是admin且密码是123456，就认为登录成功
                if (checkPassword(account,password)) {
                    editor = pref.edit();
                    if (remeberPass.isChecked()) {    //检查复选框是否被选中
                        editor.putBoolean("remeber_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, RecognizeActivity.class);
                    intent.putExtra("username",account);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkPassword(String username,String password){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        cursor=db.rawQuery("select count(*) from user where username =? and password =?",new String[]{username,password});
        cursor.moveToFirst();
        if(cursor.getInt(0)>0){
            return true;
        }
        return  false;
    }

}