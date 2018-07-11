package com.example.lenovo.serviceexample02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Lenovo on 2/9/2018.
 */

public class ApplyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(),"Service is in onCreate",Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String message;
        message=intent.getStringExtra("message");
        Toast.makeText(this,"Service is: "+message,Toast.LENGTH_SHORT).show();
       // stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this,"Service is in onDestroy",Toast.LENGTH_SHORT).show();
    }
}
