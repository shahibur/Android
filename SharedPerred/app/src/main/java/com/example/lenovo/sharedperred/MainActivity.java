package com.example.lenovo.sharedperred;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    EditText edt_text;
    Button btn_save,btn_display;
    TextView textView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_text=findViewById(R.id.editText);
        btn_save=findViewById(R.id.button);
        btn_display=findViewById(R.id.button2);

        textView=findViewById(R.id.textView);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferencesk=getSharedPreferences("shawonfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferencesk.edit();
                editor.putString("TEXT",edt_text.getText().toString());
                editor.apply();

            }
        });


        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferencesk=getSharedPreferences("shawonfile", Context.MODE_PRIVATE);
                String s=sharedPreferencesk.getString("TEXT","");
                textView.setText(s);
            }
        });
    }
}
