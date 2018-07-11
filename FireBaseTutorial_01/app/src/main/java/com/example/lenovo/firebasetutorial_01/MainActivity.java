package com.example.lenovo.firebasetutorial_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
           myRef = database.getReference("save using class");


    }

    public void method2(View view) {

        myRef.addValueEventListener(new ValueEventListener() {
            public static final String TAG ="firebase:" ;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this,"failed to fetch data",Toast.LENGTH_SHORT).show();
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void method1(View view) {
        String insert=editText.getText().toString();
       /* myRef.child("email : ").setValue(insert);
        myRef.child("andress : ").child("current add: ").setValue("south surma");
        myRef.child("andress : ").child("parmanent add: ").setValue("kanaighat");
        myRef.child("phone no : ").setValue("01762663904");*/


       String email = null,phone,address;

       email="shawon55@gmail.com";
       phone="013242234";
       address="london";

       User user=new User(email,address,phone);

       myRef.setValue(user);
       Toast.makeText(MainActivity.this,"insert processing",Toast.LENGTH_SHORT).show();
    }
}
