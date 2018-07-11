package com.example.lenovo.sqlitepractice;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    Button button,button2,button3,button4;
    TextView textView;
    ListView  lv;

    MySqLite mySqLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1=findViewById(R.id.editText2);
        ed2=findViewById(R.id.editText3);
        ed3=findViewById(R.id.editText4);
        ed4=findViewById(R.id.editText5);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        button3=findViewById(R.id.update);
        button2=findViewById(R.id.view);
        button4=findViewById(R.id.delete);

        mySqLite=new MySqLite(this);


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checker=mySqLite.deleteDate(ed1.getText().toString());
                if(checker==true)
                {
                    Toast.makeText(MainActivity.this,"successfully deleted",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"successfully not deleted",Toast.LENGTH_SHORT).show();
                }

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             boolean checker=mySqLite.UpdateData(ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString(),ed4.getText().toString());
               if(checker==true)
               {
                   Toast.makeText(MainActivity.this,"Successfully updated",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(MainActivity.this,"not Successfully updated",Toast.LENGTH_SHORT).show();
               }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Cursor cursor=mySqLite.Display();
               if(cursor.getCount()==0)
               {
                   Toast.makeText(MainActivity.this,"table is emptry ",Toast.LENGTH_SHORT).show();
                   return;
               }

               cursor.moveToFirst();
               StringBuffer buffer=new StringBuffer();
               do {

                   buffer.append("ID : "+cursor.getString(0)+"\n");
                   buffer.append("First Name : "+cursor.getString(1)+"\n");
                   buffer.append("Last Name : "+cursor.getString(2)+"\n");
                   buffer.append("Email : "+cursor.getString(3)+"\n\n");


               }while (cursor.moveToNext());

                displayData(buffer.toString());////cmt

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checker = mySqLite.addToAddTable(ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString(),ed4.getText().toString());
                if(checker==true)
                {
                    Toast.makeText(MainActivity.this,"Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Not inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void displayData(String data)
    {
        textView.setText(data);
    }

}
