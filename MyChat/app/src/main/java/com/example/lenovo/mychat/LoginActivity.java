package com.example.lenovo.mychat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    EditText editText_email,editText_password;
    Button button_signUp;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        toolbar=findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText_email=findViewById(R.id.editText_loginEmail);
        editText_password=findViewById(R.id.editText_loginPassword);
        button_signUp=findViewById(R.id.button_signIn);

        loadingProgress=new ProgressDialog(this);

        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;

                email=editText_email.getText().toString();
                password=editText_password.getText().toString();
                loginUserAccount(email,password);
            }
        });

    }

    private void loginUserAccount(String email, String password) {

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please write your email.",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please write your password.",Toast.LENGTH_SHORT).show();
        }
        else
        {

            loadingProgress.setTitle("Login Account");
            loadingProgress.setMessage("Please wait,while we are varify your acount");
            loadingProgress.show();
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                          loadingProgress.dismiss();
                            if(task.isSuccessful())
                            {

                                Intent mainActivitity=new Intent(LoginActivity.this,MainActivity.class);
                                mainActivitity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainActivitity);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Wrong Email and Password , Please write valid email and password.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
