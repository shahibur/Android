package com.example.lenovo.firebase_chat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lenovo.firebase_chat.Model.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class RegisterActivity extends AppCompatActivity {

    private static final String TAG ="RegisterActivity";
    EditText editText_name,editText_email,editText_password,editText_username;
    Button button_signUp;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;



    String email,password,username,gender,name;
    RadioGroup mGender;
    RadioButton mGenderOption;

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_register);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("layout ",e.getMessage());

            editText_name=findViewById(R.id.editText_name_register);
            editText_email=findViewById(R.id.editText_email_register);
            editText_password=findViewById(R.id.editText_password_register);
            editText_username=findViewById(R.id.editText_Username_register);
            button_signUp=findViewById(R.id.button_signUp_register);
            mGender=findViewById(R.id.radioGroup);

        }

        mAuth=FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance();
        mReference=mDatabase.getReference();

        try {
            email = editText_email.getText().toString();
            password = editText_password.getText().toString();
            name=editText_name.getText().toString();
            username=editText_username.getText().toString();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(RegisterActivity.this, "email and password fails"+e,
                    Toast.LENGTH_SHORT).show();
        }

        gender="male";
        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    send_new_user_data(name,username,email,gender,password);
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    Toast.makeText(RegisterActivity.this, "Authentication Successfull.",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }

                            }

                        });



            }
        });



    }



    public void send_new_user_data(String name, String username, String email, String gender, String password)
    {


        UserData userData=new UserData(name,username,email,gender,password);
        String userId=mAuth.getCurrentUser().getUid();
        mReference.child(userId).setValue(userData);

    }



}

