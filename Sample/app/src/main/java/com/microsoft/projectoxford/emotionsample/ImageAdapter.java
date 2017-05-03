package com.microsoft.projectoxford.emotionsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by Zc on 2017/5/3.
 */

public class ImageAdapter extends ArrayAdapter<ImgURI>{
    private int mResourceId;

    public ImageAdapter(Context context, int resource, int textViewResourceId, ImgURI[] objects, int resourceId) {
        super(context, resource, textViewResourceId, objects);
        this.mResourceId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(mResourceId,null);
        ImgURI imgURI=getItem(position);
        ImageView imgv1=(ImageView) view.findViewById(R.id.item1);
        ImageView imgv2=(ImageView) view.findViewById(R.id.item2);
        if(!imgURI.getUri1().toString().equals("")) imgv1.setImageURI(imgURI.getUri1());
        if(!imgURI.getUri2().toString().equals("")) imgv2.setImageURI(imgURI.getUri2());
        return view;
    }
}
