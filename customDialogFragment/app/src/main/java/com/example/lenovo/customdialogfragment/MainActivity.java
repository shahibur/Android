package com.example.lenovo.customdialogfragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button_dialogshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_dialogshow=findViewById(R.id.btnDialog);

        button_dialogshow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

                final View view=getLayoutInflater().inflate(R.layout.alert_layout,null);

                final EditText editText_email,editText_password;

                editText_email=view.findViewById(R.id.edtEmail);
                editText_password=view.findViewById(R.id.edtPass);

               /* Button btn_login=view.findViewById(R.id.buttonLogin);

                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!editText_email.getText().toString().isEmpty()
                                &&!editText_password.getText().toString().isEmpty())
                        {
                            builder.show().dismiss();
                            Toast.makeText(MainActivity.this, R.string.login_message,Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(MainActivity.this, R.string.error_message,Toast.LENGTH_SHORT).show();

                    }
                });*/


                final Spinner spinner=view.findViewById(R.id.spinner);

                ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.spinnerList));
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(arrayAdapter);


                 builder.setTitle("AlerDialog");

               builder.setPositiveButton("login", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                           }
                       });

               builder.setNegativeButton("dismiss", new DialogInterface.OnClickListener() {

                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                   }
               });
               builder.setView(view);
               final AlertDialog alertDialog=builder.create();
                alertDialog.show();

                alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     if(!spinner.getSelectedItem().toString().equals("choose any option")) {
                         alertDialog.dismiss();
                         Toast.makeText(MainActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                     }
                    }
                });


            }


        });



    }
}
