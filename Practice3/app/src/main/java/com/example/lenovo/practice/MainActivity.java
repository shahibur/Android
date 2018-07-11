package com.example.lenovo.practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import  android.app.FragmentTransaction;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    FragmentA fragA=new FragmentA();
    FragmentB fragB=new FragmentB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void  FragmentA(View view)
    {
        FragmentManager fm=getFragmentManager();

         FragmentTransaction ft=fm.beginTransaction();

           ft.replace(R.id.fragment,fragA,"FragmentA");

           //  ft.add(R.id.fragment,fragA,"dkfjaslk");

            ft.commit();
    }

    public void FragmentB(View view)
    {
        FragmentManager fm=getFragmentManager();

        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment,fragB,"FragmentB");

        //  ft.add(R.id.fragment,fragA,"dkfjaslk");

        ft.commit();
    }


}
