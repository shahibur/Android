package com.example.lenovo.chathomes;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by Lenovo on 3/6/2018.
 */

public class MyChat_offline extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        //for retreiving string type data from firebase without database
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        ///for retreiving picture from firebase without database

        Picasso.Builder builder=new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
        Picasso built=builder.build();

        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);


    }
}
