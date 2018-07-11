package com.example.lenovo.chathomes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.mychat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editText_name,editText_password,editText_email;
    private Button button_create;
    private ProgressDialog progressLoading;

    private DatabaseReference storeDefaultDatabaseReference;


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        toolbar=findViewById(R.id.register_toolbar);

        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Sign Up");
        } catch (Exception e) {
            e.printStackTrace();
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressLoading=new ProgressDialog(this);
        editText_email=findViewById(R.id.register_editText_email);
        editText_name=findViewById(R.id.register_editText_name);
        editText_password=findViewById(R.id.register_editText_password);

        button_create=findViewById(R.id.register_button_create);

        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=editText_name.getText().toString();
                String email=editText_email.getText().toString();
                String password=editText_password.getText().toString();
                registerAccount(name,email,password);


            }
        });


    }

    private void registerAccount(final String name, String email, String password) {

        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please write your name",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please write your email",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please write your password",Toast.LENGTH_SHORT).show();
        }
        else {
            progressLoading.setTitle("Creating New Account");
            progressLoading.setMessage("Please wait, While we are creating account for you.");
            progressLoading.show();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {

                              String current_user_id=mAuth.getCurrentUser().getUid();

                              String  deviceToken= FirebaseInstanceId.getInstance().getToken();
                                Log.e("r_deviceToken: ",deviceToken);

                               storeDefaultDatabaseReference= FirebaseDatabase.getInstance()
                                    .getReference().child("Users")
                                    .child(current_user_id);

                               storeDefaultDatabaseReference.child("user_name").setValue(name);
                               storeDefaultDatabaseReference.child("user_status").setValue("Hey there , I'm using myChat App.");
                               storeDefaultDatabaseReference.child("user_image").setValue("default_profile");
                               storeDefaultDatabaseReference.child("device_token").setValue(deviceToken);
                               storeDefaultDatabaseReference.child("user_thumbs_image").setValue("default_image")
                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if(task.isSuccessful())
                                       {
                                           Intent mainActivity=new Intent(RegisterActivity.this,MainActivity.class);
                                           mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                           startActivity(mainActivity);
                                           finish();
                                       }
                                   }
                               });
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this,"Error occured,Please try again",Toast.LENGTH_SHORT).show();
                            }

                            progressLoading.dismiss();
                        }
                    });
        }
    }
}
