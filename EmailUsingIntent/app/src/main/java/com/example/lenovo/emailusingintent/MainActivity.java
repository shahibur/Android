package com.example.lenovo.emailusingintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendEmail(View view) {
        String [] to={"shahiburrahman55@gmail.com"};
        String subject="Mail from shawon's app";
        String message="Mail body!!";

        Intent email=new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");

        email.putExtra(Intent.EXTRA_EMAIL,to);
        email.putExtra(Intent.EXTRA_SUBJECT,subject);
        email.putExtra(Intent.EXTRA_TEXT,message);

       try {
           startActivity(Intent.createChooser(email,"Send Mail..."));
       }
       catch (Exception e)
       {
           Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
       }


    }
}
