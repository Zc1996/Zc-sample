package com.microsoft.projectoxford.emotionsample.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.microsoft.projectoxford.emotionsample.R;

import java.util.List;

/**
 * Created by Zc on 2017/5/10.
 */

public class StatisticAdapter extends ArrayAdapter<StatisticItem> {
    private int resourceId;

    public StatisticAdapter(Context context, int resource, List<StatisticItem> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView username=(TextView)view.findViewById(R.id.item_username);
        TextView age=(TextView)view.findViewById(R.id.item_age);
        TextView sex=(TextView)view.findViewById(R.id.item_sex);
        TextView date=(TextView)view.findViewById(R.id.item_date);
        LinearLayout title=(LinearLayout)view.findViewById(R.id.item_title);
        StatisticItem item=getItem(position);
        if(item.getTitle()){
            username.setText(item.getUsername());
            age.setText(item.getAge());
            sex.setText(item.getSex());
            date.setVisibility(View.GONE);
        }else{
            title.setVisibility(View.GONE);
            date.setText(item.getDate());
        }
        return view;
    }
}
