package com.example.lenovo.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


/*
        FirstFragment firstFragment=new FirstFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer,firstFragment)
                .commit();*/

     /*      <fragment
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:name="com.example.lenovo.fragment.FirstFragment"
        android:layout_gravity="center"
        tools:layout="@layout/fragment_first"
        android:id="@+id/TODO"/>
      */



    }


    public void addFragment(View view) {

        android.support.v4.app.Fragment fragment=getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainer);
        Log.e("","error0:",new Exception());
        if(fragment==null) {

            Log.e("","error00:",new Exception());
            FirstFragment firstFragment =FirstFragment.newInstance("New Fragment Created");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, firstFragment)
                    .commit();
        }

    }

    @SuppressLint("ResourceType")
    public void removeFragment(View view) {
     /*   Toast.makeText(MainActivity.this, view.getId(),Toast.LENGTH_SHORT).show();
        try {
            Fragment fragment=getSupportFragmentManager()
                    .findFragmentById(R.id.fragmentContainer);
            Log.e("","error1:",new Exception());
            if(fragment!=null) {
                Log.e("","error2:",new Exception());
                getFragmentManager().
                        beginTransaction()
                        .remove(fragment).commit();
            }

        }catch (Exception e)
        {
            Toast.makeText(MainActivity.this, (CharSequence) e,Toast.LENGTH_SHORT).show();
        }

*/
                android.support.v4.app.Fragment fragment=getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainer);

                if(fragment!=null)
                {
                    getSupportFragmentManager().beginTransaction()
                            .remove(fragment)
                            .commit();

                }




    }
}
