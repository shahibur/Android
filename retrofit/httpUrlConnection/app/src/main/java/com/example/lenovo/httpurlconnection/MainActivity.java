package com.example.lenovo.httpurlconnection;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
// example of asynctask thread
public class MainActivity extends AppCompatActivity {

    TextView textView,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
/*
        try {
            URL url=new URL("http://www.google.com");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            Log.i("ResponeToApp :",String.valueOf(httpURLConnection.getResponseCode()));
          ///  textView.setText(String.valueOf(httpURLConnection.getResponseCode()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("error1: ",e.getMessage(),e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("error2: ",e.getMessage(),e);
        }
    }*/

     new CheckAsyncTasks().execute("http://www.google.com");
   /*
    new Thread(new Runnable() {
       @Override
       public void run() {
           while (true)
           {
               int s=34;
           }
       }
   }).start();*/


     /*      new Thread(new Runnable() {
               @Override
               public void run() {
                   try {
                       URL url=new URL("http://www.google.com");
                       HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                       Log.i("ResponeToApp :",String.valueOf(httpURLConnection.getResponseCode()));
                       textView.setText(String.valueOf(httpURLConnection.getResponseCode()));
                   } catch (MalformedURLException e) {
                       e.printStackTrace();
                       Log.e("error1: ",e.getMessage(),e);
                   } catch (IOException e) {
                       e.printStackTrace();
                       Log.e("error2: ",e.getMessage(),e);
                   }
               }
           }).start();*/
    }


    class CheckAsyncTasks extends AsyncTask<String,Void,String>
    {

            ///ctrl+j
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            textView.setText("");
            textView2.setText("onPreExecuteRespone is showing ");
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url=new URL(strings[0]);///strings is an array which indicates first position of parameters
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                return String.valueOf(httpURLConnection.getResponseCode());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("error1: ",e.getMessage(),e);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("error2: ",e.getMessage(),e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
          Log.i("ResponeToApp :",s);
            textView.setText(s);
        }
    }
}
