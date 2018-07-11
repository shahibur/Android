package com.example.lenovo.listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



     String [] name={"sylhet","shawon","dhaka","chittagong","london","billionaire","dubai","france","canada"};
     ListView listView;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);


        final ListAdapter adapter=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,name);
        listView.setAdapter(adapter);



      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              Toast.makeText(MainActivity.this,String.valueOf(parent.getItemAtPosition(position)),Toast.LENGTH_SHORT).show();


              AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
              builder.setTitle("Hello User");
              builder.setMessage("you clicked on "+String.valueOf(parent.getItemAtPosition(position)));
              builder.create();
              builder.show();
          }
      });
    }



/*
   public  static String Tag="MainActivity";
   public ArrayAdapter arrayAdapter;
   ListView list;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=findViewById(R.id.listView);
     ///  Log.d(Tag,"tag occurred");
        final ArrayList<String> names=new ArrayList<>();

        names.add("sylhet");
        names.add("dhaka");
        names.add("london");
        names.add("shawon");
        arrayAdapter=new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,names);

        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,names.get(position),Toast.LENGTH_SHORT).show();
            }
        });
       /// arrayAdapter.set

    }
*/

    }
