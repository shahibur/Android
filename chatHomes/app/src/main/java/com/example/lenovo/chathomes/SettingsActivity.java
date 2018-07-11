package com.example.lenovo.chathomes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mychat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;


public class SettingsActivity extends AppCompatActivity {

    private CircleImageView circleImageView_display_image;
    private TextView editText_displayName,editText_displayStatus;
    private Button button_changeImage,button_changeStatus;
    private DatabaseReference getUserDatabaseRefference;
    private StorageReference storeProfileImageRefference;
    private FirebaseAuth mAuth;
    private ProgressDialog loadBar;
    Bitmap thumb_bitmap=null;

    private StorageReference thumbImageReff;

    private final static int gellery_pic=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        circleImageView_display_image=findViewById(R.id.settings_circleImageView);
        editText_displayName=findViewById(R.id.settings_textView_userName);
        editText_displayStatus=findViewById(R.id.settings_textView_profileStatus);
        button_changeImage=findViewById(R.id.settings_button_ChangeImage);
        button_changeStatus=findViewById(R.id.settings_button_changeStatus);
        loadBar=new ProgressDialog(this);


        mAuth=FirebaseAuth.getInstance();
        String online_user_id=mAuth.getCurrentUser().getUid();
        getUserDatabaseRefference= FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(online_user_id);
        getUserDatabaseRefference.keepSynced(true);

        storeProfileImageRefference= FirebaseStorage.getInstance().getReference().child("profile_images");

        thumbImageReff=FirebaseStorage.getInstance().getReference().child("thumb_images");


        getUserDatabaseRefference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child("user_name").getValue().toString();
                String status=dataSnapshot.child("user_status").getValue().toString();
                final String image=dataSnapshot.child("user_image").getValue().toString();
                String thumb_image=dataSnapshot.child("user_thumbs_image").toString();

                editText_displayName.setText(name);
                editText_displayStatus.setText(status);
                if(!image.equals("default_profile"))
                {
                    /*Picasso.with(SettingsActivity.this).load(image).placeholder(R.drawable.default_profile)
                         .into(circleImageView_display_image);*/

                    Picasso.with(SettingsActivity.this).load(image).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.default_profile)
                            .into(circleImageView_display_image, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {

                                    Picasso.with(SettingsActivity.this).load(image).placeholder(R.drawable.default_profile)
                                            .into(circleImageView_display_image);
                                }
                            });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        button_changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent gelleryIntent=new Intent();
                gelleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                gelleryIntent.setType("image/*");
                startActivityForResult(gelleryIntent,gellery_pic);


            }
        });

        button_changeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String old_status=editText_displayStatus.getText().toString();

                Intent StatusActivity=new Intent(SettingsActivity.this,StatusActivity.class);

                StatusActivity.putExtra("user_status",old_status);

                startActivity(StatusActivity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==gellery_pic&&resultCode==RESULT_OK&&data!=null)
        {
            Uri imageUri=data.getData();
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK)
            {
                loadBar.setTitle("Updating profile image");
                loadBar.setMessage("Please wait,While we are updating your profile image...");
                loadBar.show();

                Uri resultUri = result.getUri();

                File thumb_filePathUri=new File(resultUri.getPath());

                String user_id=mAuth.getCurrentUser().getUid();

                try {

                    thumb_bitmap=new Compressor(this)
                            .setMaxWidth(200)
                            .setMaxHeight(200)
                            .setQuality(50)
                            .compressToBitmap(thumb_filePathUri);

                }catch (IOException e)
                {
                    e.printStackTrace();
                }



                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);

                final byte[] thumb_byte=byteArrayOutputStream.toByteArray();


                StorageReference filePath=storeProfileImageRefference.child(user_id+".jpg");

                final StorageReference thumb_fillPath=thumbImageReff.child(user_id+".jpg");

                filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull final Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(SettingsActivity.this,"Saving your profile image to firebase database...",Toast.LENGTH_SHORT).show();


                        final String downloadUrl=task.getResult().getDownloadUrl().toString();

                        UploadTask uploadTask=thumb_fillPath.putBytes(thumb_byte);

                        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> thumb_task) {

                                final String thumb_downloadUrl=thumb_task.getResult().getDownloadUrl().toString();

                                if(task.isSuccessful())
                                {
                                    Map update_user_data=new HashMap();
                                    update_user_data.put("user_image",downloadUrl);
                                    update_user_data.put("user_thumbs_image",thumb_downloadUrl);

                                    getUserDatabaseRefference.updateChildren(update_user_data)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(SettingsActivity.this,"Profile image updated successfully",Toast.LENGTH_SHORT).show();
                                                    loadBar.dismiss();
                                                }
                                            });

                                }
                            }
                        });


                       }
                        else {
                            Toast.makeText(SettingsActivity.this,"Error occured, While uploading your profile pic.",Toast.LENGTH_SHORT).show();
                            loadBar.dismiss();

                        }
                    }
                });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
