package com.microsoft.projectoxford.emotionsample.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.microsoft.projectoxford.emotionsample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zc on 2017/5/10.
 */

public class StatisticActivity extends ActionBarActivity{

    /*private ListView listView;
    private ArrayAdapter<StatisticItem> adapter;*/
    private List<StatisticItem> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        //listView=(ListView)findViewById(R.id.listView);
        data=new ArrayList<StatisticItem>();
        //adapter=new ArrayAdapter<StatisticItem>(StatisticActivity.this,R.layout.list_view_item,data);
        //listView.setAdapter(adapter);
        initial();
    }

    public void initial(){
        MyDatabaseHelper dbHelper=new MyDatabaseHelper(this,"user.db",null,1);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user",null);
        while(cursor.moveToNext()){
            String username=cursor.getString(cursor.getColumnIndex("username"));
            String age=cursor.getInt(cursor.getColumnIndex("age"))+"";
            String sex=cursor.getInt(cursor.getColumnIndex("sex"))==1?"男":"女";
            String date=cursor.getString(cursor.getColumnIndex("date"));
            String[] dates;
            dates=date.split("\\|");
            //Toast.makeText(StatisticActivity.this,username,Toast.LENGTH_SHORT).show();
            StatisticItem titleItem=new StatisticItem();
            titleItem.setUsername(username);
            titleItem.setAge(age);
            titleItem.setSex(sex);
            titleItem.setTitle(true);
            data.add(titleItem);
            for(String t:dates){
                if(!t.equals("")){
                    StatisticItem item=new StatisticItem();
                    item.setTitle(false);
                    item.setDate(t);
                    data.add(item);
                }
            }
        }
        LinearLayout container=(LinearLayout)findViewById(R.id.item_container);
        for(StatisticItem item:data){
            /*View view=getLayoutInflater().inflate(R.layout.list_view_item,container);
            TextView username=(TextView)findViewById(R.id.item_username);
            TextView age=(TextView)findViewById(R.id.item_age);
            TextView sex=(TextView)findViewById(R.id.item_sex);
            TextView date=(TextView)findViewById(R.id.item_date);
            LinearLayout title=(LinearLayout)findViewById(R.id.item_title);
            if(item.getTitle()){
                username.setText(item.getUsername());
                age.setText(item.getAge());
                sex.setText(item.getSex());
                date.setText("");
                date.setVisibility(View.GONE);

            }else{
                date.setText(item.getDate());
                username.setText("");
                age.setText("");
                sex.setText("");
                title.setVisibility(View.GONE);
            }
            container.addView(view);*/

            if(item.getTitle()){
                /*LinearLayout linearLayout=new LinearLayout(this);
                TextView tv1=new TextView(this);
                TextView tv2=new TextView(this);
                TextView tv3=new TextView(this);
                *//*LinearLayout.LayoutParams lllp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLayout.setLayoutParams(lllp);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams lp1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                LinearLayout.LayoutParams lp2=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                LinearLayout.LayoutParams lp3=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);*//*
                *//*tv1.setLayoutParams(lp1);
                tv2.setLayoutParams(lp2);
                tv3.setLayoutParams(lp3);*//*
                tv1.setText(item.getUsername());
                tv2.setText(item.getAge());
                tv3.setText(item.getSex());
                tv1.setTextSize(18);
                tv3.setTextSize(18);
                tv3.setTextSize(18);
                tv1.setGravity(Gravity.CENTER);
                tv2.setGravity(Gravity.CENTER);
                tv3.setGravity(Gravity.CENTER);
                tv1.setTextColor(Color.BLACK);
                tv2.setTextColor(Color.BLACK);
                tv3.setTextColor(Color.BLACK);
                linearLayout.addView(tv1);
                linearLayout.addView(tv2);
                linearLayout.addView(tv3);
                container.addView(linearLayout);*/
                TextView textView=new TextView(this);
                String space="    ";
                textView.setText(item.getUsername()+space+item.getAge()+space+item.getSex());
                LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(lp);
                textView.setTextSize(20);
                textView.setTextColor(Color.BLACK);
                container.addView(textView);
            }else{
                TextView textView=new TextView(this);
                textView.setText(item.getDate());
                LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(lp);
                textView.setTextSize(20);
                textView.setTextColor(Color.BLACK);
                container.addView(textView);
            }
        }
        //adapter.notifyDataSetChanged();
    }
}
