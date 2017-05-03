package com.microsoft.projectoxford.emotionsample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zc on 2017/5/3.
 */

public class ShowImagesActivity extends ActionBarActivity{
    private String uri;
    private ListView listView;
    private ArrayAdapter<ImgURI> adapter;
    private List<ImgURI> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
        Intent intent=getIntent();
        uri=intent.getStringExtra("uri");
        listView=(ListView)findViewById(R.id.list_view);
        data=new ArrayList<ImgURI>();
        //initial();
        adapter=new ArrayAdapter<ImgURI>(ShowImagesActivity.this,R.layout.image_item,data);
        listView.setAdapter(adapter);
    }

    public void initial(){
        String []uris=uri.split("\\|");
        ImgURI imgURI;
        for(int i=0;i<uris.length;i+=2){
            imgURI=new ImgURI();
            imgURI.setUri1(Uri.parse(uris[i]));
            if(i+1<uris.length){
                imgURI.setUri2(Uri.parse(uris[i+1]));
            }else{
                imgURI.setUri2(Uri.EMPTY);
            }
            data.add(imgURI);
        }
    }
}
