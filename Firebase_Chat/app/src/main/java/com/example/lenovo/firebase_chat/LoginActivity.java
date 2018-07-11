package com.example.lenovo.firebase_chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText editText_email,editText_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editText_email=findViewById(R.id.editText_email_login);
        editText_password=findViewById(R.id.editText_password_login);



    }



}
