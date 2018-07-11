package com.example.lenovo.customalertdialog;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
               // builder.setTitle("This is title");
               // builder.setMessage("This is AlertDialog for you");

                LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
                final View inf=inflater.inflate(R.layout.custom,null);
                builder.setView(inf);

                final EditText ed1_name=inf.findViewById(R.id.editText);
                final EditText ed2_password=inf.findViewById(R.id.editText2);
                final Button btn_login=inf.findViewById(R.id.button2);
                final  AlertDialog alertDialog=builder.create();
                alertDialog.show();
                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String s=ed1_name.getText().toString()+" "+ed2_password.getText().toString();
                        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });


            }
        });
    }
}
