package com.example.lenovo.firebaseauthentication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Profile extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 124;
    ImageView imageView;
    TextView textView_top,textView_verified;
    EditText editText_display_name;
    Button button_save;

// FIREBASE DATABASE
    Uri ureProfileImage;
    FirebaseAuth firebaseAuth;


    ProgressBar progressBar;
    String profileImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();

        Toolbar toolbar=findViewById(R.id.toolbar);

        try {
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageView=findViewById(R.id.imageView);
        button_save=findViewById(R.id.button_save);
        progressBar=findViewById(R.id.progressBar3);
        editText_display_name=findViewById(R.id.editText_des);
        textView_verified=findViewById(R.id.textViewVerified);


        loadUserInformation();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();

            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveUserInformation();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();

        menuInflater.inflate(R.menu.menu,menu);


       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }


        return true;
    }

    private void loadUserInformation() {
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        if (firebaseUser != null) {
            if (firebaseUser.getPhotoUrl() != null) {
                Glide.with(this).load(firebaseUser.getPhotoUrl().toString())
                        .into(imageView);
            }
         if(firebaseUser.getDisplayName()!=null)
         {
             editText_display_name.setText(firebaseUser.getDisplayName());
         }
         if (firebaseUser.isEmailVerified())
         {
             textView_verified.setText("Email is verified");
         }
         else
         {
             textView_verified.setText("Email is not verified(Click here for verify)");
             textView_verified.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             Toast.makeText(getApplicationContext(),"Verification Email sent",Toast.LENGTH_SHORT).show();

                         }
                     });
                 }
             });

         }

        }
    }

    private void saveUserInformation() {

        String name=editText_display_name.getText().toString();

        if(name.isEmpty())
        {
            editText_display_name.setError("name required");
            editText_display_name.requestFocus();
            return;
        }


        //// firebaseUser
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();





        if(firebaseUser!=null&& profileImageUrl!=null)
        {
            UserProfileChangeRequest userProfileChangeRequest= new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .setPhotoUri(Uri.parse(profileImageUrl))
                    .build();

            firebaseUser.updateProfile(userProfileChangeRequest)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Profile.this,"Profile updated",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CHOOSE_IMAGE&&resultCode==RESULT_OK&& data !=null && data.getData()!=null)
        {
            ureProfileImage=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),ureProfileImage);
                imageView.setImageBitmap(bitmap);
                uploadImageToFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseStorage() {
        StorageReference storageReference= FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis()
        +".jpg");

        if(ureProfileImage!=null)
        {
            progressBar.setVisibility(View.VISIBLE);
            storageReference.putFile(ureProfileImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);

                            profileImageUrl=taskSnapshot.getDownloadUrl().toString();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private  void showImageChooser()
    {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select profile image"),CHOOSE_IMAGE);

    }
}
