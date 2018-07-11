package com.example.lenovo.asyncpractice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    EditText editText_num1,editText_num2;
    Button button;
    String result;
    String url="http://www.google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText_num1=findViewById(R.id.editText_num1);

        editText_num2=findViewById(R.id.editText2_num2);

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(editText_num1.getText().toString());
                int b=Integer.parseInt(editText_num2.getText().toString());

                new MultipleTasks().execute(url);
            }
        });
    }



    class MultipleTasks extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);

            Toast.makeText(MainActivity.this,"value :"+s,Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url_link=new URL(strings[0]);


                HttpURLConnection httpURLConnection= (HttpURLConnection) url_link.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();


                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

               String value=bufferedReader.readLine();
                result=value;
               System.out.print("value "+value);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return result;
        }
    }
}
