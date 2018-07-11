package com.example.lenovo.mychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartPageActivity extends AppCompatActivity {

    Button button_alreadyAccount,button_newAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        button_newAccount=findViewById(R.id.buttonNeedAccount);
        button_alreadyAccount=findViewById(R.id.button_alreadAccount);

        button_alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginActivity=new Intent(StartPageActivity.this,LoginActivity.class);
                startActivity(loginActivity);
            }
        });

        button_newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity=new Intent(StartPageActivity.this,RegisterActivity.class);
                startActivity(registerActivity);
            }
        });
    }
}
