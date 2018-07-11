package com.example.lenovo.firebaseauthentication;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText_email,editText_password;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.button_login).setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        editText_email=findViewById(R.id.editText_username);
        editText_password=findViewById(R.id.editText_password1);
        progressBar=findViewById(R.id.progressBar1);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.textViewSignup:

                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            //    finish();
                break;

            case  R.id.button_login:
                userLogin();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(this,Profile.class));
        }
    }

    private void userLogin() {
        String email, password;

        email = editText_email.getText().toString().trim();
        password = editText_password.getText().toString().trim();

        if (email.isEmpty()) {
            editText_email.setError("Email is required");
            editText_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText_email.setError("Please enter a validate email");
            editText_email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editText_email.setError("password is required");
            editText_email.requestFocus();
            return;
        }
        if (password.length() < 6)
        {
            editText_email.setError("Minimum lenght of password should be 6");
            editText_email.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    finish();
                    Intent intent=new Intent(MainActivity.this,Profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
