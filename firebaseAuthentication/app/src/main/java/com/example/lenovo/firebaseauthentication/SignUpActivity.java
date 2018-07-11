package com.example.lenovo.firebaseauthentication;

        import android.content.Intent;
        import android.media.MediaCodec;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Patterns;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseAuthUserCollisionException;
        import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText_email,editText_password;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewAlreadyLogin).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        editText_email=findViewById(R.id.editTextEmail);
        editText_password=findViewById(R.id.editTextPassword);
        progressBar=findViewById(R.id.progressBar2);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.buttonSignUp:
                UserRegister();
                break;

            case R.id.textViewAlreadyLogin:

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                break;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
      /*  if(mAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }*/
    }

    private void UserRegister() {

        String email, password;

        email = editText_email.getText().toString().trim();
        password = editText_password.getText().toString().trim();

        if (email.isEmpty()) {
            editText_email.setError("Email is required");
            editText_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
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
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                   /// Toast.makeText(getApplicationContext(),"User registered sussessfull",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent=new Intent(getApplicationContext(),Profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {

                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"Email is already registered",Toast.LENGTH_SHORT).show();
                    }
                    else
                     Toast.makeText(getApplicationContext(),"User failed",Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}
