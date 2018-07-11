package com.example.lenovo.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText e1,e2;
    TextView t1,t2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

        e1=findViewById(R.id.editText1);
        e2=findViewById(R.id.editText2);
        t1=findViewById(R.id.showResult);



    }
    public void FunctionAdd(View view)
    {
        int num1,num2,result;

        if(view.getId()==R.id.buttonAdd) {
            num1 = Integer.parseInt(e1.getText().toString());

            num2 = Integer.parseInt(e2.getText().toString());

            result = num1 + num2;

            t1.setText("Result is " + Integer.toString(result));
        }
       else if(view.getId()==R.id.button4)
        {
            num1 = Integer.parseInt(e1.getText().toString());

            num2 = Integer.parseInt(e2.getText().toString());

            result = num1 - num2;

            t1.setText("Result is " + Integer.toString(result));
        }
        else if(view.getId()==R.id.mul)
        {
            num1 = Integer.parseInt(e1.getText().toString());

            num2 = Integer.parseInt(e2.getText().toString());

            result = num1 * num2;

            t1.setText("Result is " + Integer.toString(result));
        }
        else if(view.getId()==R.id.div)
        {
            num1 = Integer.parseInt(e1.getText().toString());

            num2 = Integer.parseInt(e2.getText().toString());

            result = num1 / num2;

            t1.setText("Result is " + Integer.toString(result));
        }
    }

}
