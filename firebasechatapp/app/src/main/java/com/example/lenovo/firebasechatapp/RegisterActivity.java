package com.example.lenovo.firebasechatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.lenovo.firebasechatapp.Model.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {


    private static final String TAG ="registerActivity";
    ImageView back_bt;
    CircleImageView circleImageView;
    TextInputEditText mName,mEmail,mUsername,mPassword;
    Button mRegister;
    RadioGroup mGender;
    RadioButton mGenderOption;
    String email,password,username,gender,name;


    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog progressLoading;



 ///   Firebase_Method firebase_method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        back_bt=findViewById(R.id.back_activity);
        circleImageView=findViewById(R.id.profile_image);
        mName=findViewById(R.id.et_name);
        mEmail=findViewById(R.id.et_email);
        mUsername=findViewById(R.id.et_username);
        mPassword=findViewById(R.id.et_password);

        mRegister=findViewById(R.id.button_signIn);

        mGender=findViewById(R.id.radioGroup);

        email = mEmail.getText().toString();
        password = mPassword.getText().toString();
        name=mName.getText().toString();
        username=mUsername.getText().toString();
        gender="male";

        mAuth=FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance();
        mReference=mDatabase.getReference();

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(RegisterActivity.this,email+"\n"+password,Toast.LENGTH_SHORT).show();
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    Toast.makeText(RegisterActivity.this, "Authentication Successfull.",
                                            Toast.LENGTH_SHORT).show();
                                    registerAccount(name,username,email,gender,password);

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

    }//.....oncreate close......

    private void registerAccount(final String name, final String username, final String email, final String gender, final String password) {

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

                                Toast.makeText(RegisterActivity.this,"deviceToken:"+deviceToken,Toast.LENGTH_SHORT).show();

                                Log.e("register_current_user",current_user_id);
                                Log.e("r_deviceToken: ",deviceToken);

                                UserData userData=new UserData(name,username,email,gender,password);


                                String userId=mAuth.getCurrentUser().getUid();
                                mReference.child(userId).setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    Toast.makeText(RegisterActivity.this, "Registation Successfull.",
                                                            Toast.LENGTH_SHORT).show();
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
