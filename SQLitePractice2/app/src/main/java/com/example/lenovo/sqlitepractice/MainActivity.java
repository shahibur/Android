package com.example.lenovo.sqlitepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    Button button;
    SQLiteFunc sqLiteFunc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);
        ed3=findViewById(R.id.editText3);
        button=findViewById(R.id.button);
        sqLiteFunc=new SQLiteFunc(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              boolean check=sqLiteFunc.insertIntoSQLiteDb(ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString());

              if(check==true)
              {
                  Toast.makeText(MainActivity.this,"Successfully inserted",Toast.LENGTH_SHORT).show();
              }
              else
                  Toast.makeText(MainActivity.this,"Successfully inserted",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
