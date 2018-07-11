package com.example.lenovo.firebase_tutorial_002;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "firebase_date ";
    DatabaseReference myRef;
    Button button_insert,button_display;
    EditText editText_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_display=findViewById(R.id.button_display);
        button_insert=findViewById(R.id.button_insert);
        editText_insert=findViewById(R.id.editText_insert);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("arrayData");

        insertDisplayFirebaseData();
    }

    void insertDisplayFirebaseData()
    {
        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              ///  String hello=editText_insert.getText().toString();

                String name="shawon";
                int age=22;
                double salary=434345.43;
                InserData inserData=new InserData(name,age,salary);
                myRef.child("user info").setValue(inserData);
                Toast.makeText(MainActivity.this,"inserted",Toast.LENGTH_SHORT).show();
            }
        });

        button_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d(TAG, "Value is: " + value);
                        Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });
    }
}
