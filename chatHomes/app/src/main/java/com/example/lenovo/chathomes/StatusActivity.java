package com.example.lenovo.chathomes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.lenovo.mychat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar_status_bar;
    private Button button_status_save;
    private ProgressDialog loadingDialog;
    EditText editText_Status;
    private FirebaseAuth mAuth;
    private DatabaseReference changeStatusDatabaseReff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);


        toolbar_status_bar=findViewById(R.id.status_app_bar);

        try {
            setSupportActionBar(toolbar_status_bar);
            getSupportActionBar().setTitle("Change Status");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        loadingDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        String user_id=mAuth.getCurrentUser().getUid();
        changeStatusDatabaseReff=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


        editText_Status=findViewById(R.id.editText_statusActivity);

        button_status_save=findViewById(R.id.statusActivity_button_save);

        String old_status= getIntent().getExtras().get("user_status").toString();
        editText_Status.setText(old_status);

        button_status_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String new_string=editText_Status.getText().toString();

                changeProfileStatus(new_string);

            }
        });


    }

    private void changeProfileStatus(String new_string) {

        if(TextUtils.isEmpty(new_string))
        {
            Toast.makeText(StatusActivity.this,"Please write your status..",Toast.LENGTH_SHORT).show();
        }
        else
        {

            loadingDialog.setTitle("Change Profile Status");
            loadingDialog.setMessage("Please wait, while are updating your profile status");
            loadingDialog.show();
            changeStatusDatabaseReff.child("user_status").setValue(new_string)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Intent SettingsActivitity=new Intent(StatusActivity.this,SettingsActivity.class);
                                SettingsActivitity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(SettingsActivitity);

                                Toast.makeText(StatusActivity.this,"Profile status updated successfully",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(StatusActivity.this,"Error occured, Please try again...",Toast.LENGTH_SHORT).show();
                            }
                            loadingDialog.dismiss();
                        }
                    });
        }
    }
}
