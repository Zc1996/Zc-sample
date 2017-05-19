package com.microsoft.projectoxford.emotionsample.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private int []stats=new int[10];
    private int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        //listView=(ListView)findViewById(R.id.listView);
        data=new ArrayList<StatisticItem>();
        for(int i=0;i<10;i++){
            stats[i]=0;
        }
        sum=0;
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
                    String []str;
                    str=t.split(" ");
                    String []time;
                    time=str[1].split(":");
                    int hour=Integer.parseInt(time[0]);
                    int minute=Integer.parseInt(time[1]);
                    int cur=-1;
                    if(hour>=12&&hour<17){
                        sum++;
                        cur=(hour-12)*2;
                        if(minute>=30) cur++;
                    }
                    if(cur!=-1) stats[cur]++;
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
        RelativeLayout chartContainer=(RelativeLayout)findViewById(R.id.chartContainer);
        final  TextView []tv=new TextView[10];
        tv[0]=(TextView)findViewById(R.id.chart0);
        tv[1]=(TextView)findViewById(R.id.chart1);
        tv[2]=(TextView)findViewById(R.id.chart2);
        tv[3]=(TextView)findViewById(R.id.chart3);
        tv[4]=(TextView)findViewById(R.id.chart4);
        tv[5]=(TextView)findViewById(R.id.chart5);
        tv[6]=(TextView)findViewById(R.id.chart6);
        tv[7]=(TextView)findViewById(R.id.chart7);
        tv[8]=(TextView)findViewById(R.id.chart8);
        tv[9]=(TextView)findViewById(R.id.chart9);
        //String t="";
        for(int i=0;i<10;i++){
            tv[i].getLayoutParams().height=100*stats[i]/sum;
            //t+="|"+stats[i];
        }
        //Toast.makeText(StatisticActivity.this,t,Toast.LENGTH_SHORT).show();
        //adapter.notifyDataSetChanged();
    }
}
