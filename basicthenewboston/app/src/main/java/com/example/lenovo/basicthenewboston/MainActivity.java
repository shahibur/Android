package com.example.lenovo.basicthenewboston;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /// setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout=new RelativeLayout(this);
        relativeLayout.setBackgroundColor(Color.BLUE);
        Button button=new Button(this);
        button.setText("Login In");
        button.setBackgroundColor(Color.RED);

        ///user name input
        EditText username=new EditText(this);
        button.setId(1);
        username.setId(2);


        RelativeLayout.LayoutParams buttonPosition= new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT

        );

        RelativeLayout.LayoutParams usernamePosition= new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT

        );

        buttonPosition.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonPosition.addRule(RelativeLayout.CENTER_VERTICAL);



        relativeLayout.addView(button,buttonPosition);
        setContentView(relativeLayout);
    }
}
