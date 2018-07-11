package com.example.lenovo.fragmentpractice;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button button_A,button_B,button_C;
    A_Fragment a_fragment=new A_Fragment();
    B_Fragment b_fragment=new B_Fragment();
    C_Fragment c_fragment=new C_Fragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button_A=findViewById(R.id.buttonA);
        button_B=findViewById(R.id.buttonB);
      ///  button_C=findViewById(R.id.buttonC);
        button_C=findViewById(R.id.button);

   /*     getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fagmentdisplay,"")
                .commit();*/
    }
    android.support.v4.app.Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.fagmentdisplay);

    public void showFragment(View view) {

        if(fragment==null) {
            if (view == button_A) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fagmentdisplay, a_fragment)
                        .commit();
            }
            if (view == button_B) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fagmentdisplay, b_fragment)
                        .commit();
            }
            if (view == button_C) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fagmentdisplay, c_fragment)
                        .commit();
            }
        }
    }
}
