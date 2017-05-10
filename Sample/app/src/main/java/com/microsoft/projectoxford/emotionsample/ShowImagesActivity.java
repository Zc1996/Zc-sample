package com.microsoft.projectoxford.emotionsample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.microsoft.projectoxford.emotionsample.helper.ImageHelper;

/**
 * Created by Zc on 2017/5/3.
 */

public class ShowImagesActivity extends ActionBarActivity{
    private String uri;
    //private ListView listView;
    //private ArrayAdapter<ImgURI> adapter;
    //private List<ImgURI> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
        Intent intent=getIntent();
        uri=intent.getStringExtra("uri");
        //Toast.makeText(ShowImagesActivity.this,uri,Toast.LENGTH_SHORT ).show();
        //listView=(ListView)findViewById(R.id.list_view);
        //data=new ArrayList<ImgURI>();
        initial();
        //adapter=new ArrayAdapter<ImgURI>(ShowImagesActivity.this,R.layout.image_item,data);
        //listView.setAdapter(adapter);
    }//960711

    public void initial(){
        String []uris=uri.split("\\|");
        ImageView img=(ImageView)findViewById(R.id.imgTest);
        Toast.makeText(ShowImagesActivity.this,uris[0], Toast.LENGTH_SHORT).show();
        Uri mImageUri=Uri.parse(uri);
        Bitmap mBitmap = ImageHelper.loadSizeLimitedBitmapFromUri(
                mImageUri, getContentResolver());
        if (mBitmap != null) {
            img.setImageBitmap(mBitmap);
        }
        //ImgURI imgURI;
        /*for(int i=0;i<uris.length;i+=2){
            imgURI=new ImgURI();
            imgURI.setUri1(Uri.parse(uris[i]));
            if(i+1<uris.length){
                imgURI.setUri2(Uri.parse(uris[i+1]));
            }else{
                imgURI.setUri2(Uri.EMPTY);
            }
            data.add(imgURI);
        }*/
        /*for(int i=0;i<uris.length;i++){
            imgURI=new ImgURI();
            imgURI.setUri1(Uri.parse(uris[i]));
            imgURI.setUri2(Uri.parse(uris[i]));
            data.add(imgURI);
        }*/
    }


}
