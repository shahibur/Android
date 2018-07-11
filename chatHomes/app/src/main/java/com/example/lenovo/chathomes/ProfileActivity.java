package com.example.lenovo.chathomes;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mychat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    private Button button_sendRequest, button_declineRequest;

    private TextView textView_userName, textView_userStatus;

    ImageView imageView_userImage;

    private DatabaseReference usersRefference;

    private String CURRENT_STATE;
    private DatabaseReference friendRequestRefference;
    private DatabaseReference friendRefference;
    private DatabaseReference notificationRefference;
    private FirebaseAuth mAuth;
    private String sender_user_id;
    String receiver_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        receiver_user_id = getIntent().getExtras().get("visit_user_id").toString();


        button_declineRequest = findViewById(R.id.profile_button_declineRequest);
        button_sendRequest = findViewById(R.id.profile_button_sendRequest);
        textView_userName = findViewById(R.id.profile_textView_username);
        textView_userStatus = findViewById(R.id.profile_textView_userStatus);
        imageView_userImage = findViewById(R.id.profileActivity_userImage);

        CURRENT_STATE = "not_friends";


        ///creating user's folder
        usersRefference = FirebaseDatabase.getInstance().getReference().child("Users");


        ///creating user's friends folder
        friendRefference = FirebaseDatabase.getInstance().getReference().child("Friends");
        friendRefference.keepSynced(true);

        notificationRefference=FirebaseDatabase.getInstance().getReference().child("Notifications");
        notificationRefference.keepSynced(true);



        ///creating user's requests folder
        friendRequestRefference = FirebaseDatabase.getInstance().getReference().child("friend_requests");

        friendRequestRefference.keepSynced(true);


        /// finding current user id
        mAuth = FirebaseAuth.getInstance();
        sender_user_id = mAuth.getCurrentUser().getUid();


        usersRefference.child(receiver_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("user_name").getValue().toString();
                String status = dataSnapshot.child("user_status").getValue().toString();
                String image = dataSnapshot.child("user_image").getValue().toString();


                textView_userName.setText(name);
                textView_userStatus.setText(status);
                Picasso.with(ProfileActivity.this).load(image).placeholder(R.drawable.default_profile)
                        .into(imageView_userImage);


                friendRequestRefference.child(sender_user_id)
                        .addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                if (dataSnapshot.exists()) {
                                    if (dataSnapshot.hasChild(receiver_user_id)) {
                                        String req_type = dataSnapshot.child(receiver_user_id)
                                                .child("request_type")
                                                .getValue().toString();
                                        if (req_type.equals("sent")) {
                                            CURRENT_STATE = "request_sent";
                                            button_sendRequest.setText("Cancel friend request");

                                            button_declineRequest.setVisibility(View.INVISIBLE);
                                            button_declineRequest.setEnabled(false);
                                        } else if (req_type.equals("received")) {
                                            CURRENT_STATE = "request_received";
                                            button_sendRequest.setText("Accept friend request");

                                            button_declineRequest.setVisibility(View.VISIBLE);
                                            button_declineRequest.setEnabled(true);

                                            button_declineRequest.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    declineFriendRequest();
                                                }
                                            });

                                        }
                                    }

                                } else
                                {
                                    friendRefference.child(sender_user_id)
                                            .addListenerForSingleValueEvent(new ValueEventListener() {

                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    if(dataSnapshot.hasChild(receiver_user_id))
                                                    {
                                                        CURRENT_STATE="friends";
                                                        button_sendRequest.setText("Unfriend this person");


                                                        button_declineRequest.setVisibility(View.INVISIBLE);
                                                        button_declineRequest.setEnabled(false);
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });

        button_declineRequest.setVisibility(View.INVISIBLE);
        button_declineRequest.setEnabled(false);

       if(!sender_user_id.equals(receiver_user_id))
        {
            button_sendRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    button_sendRequest.setEnabled(false);

                    if (CURRENT_STATE.equals("not_friends")) {
                        sendFriendRequestToAUeser();
                    }
                    if (CURRENT_STATE.equals("request_sent")) {
                        cancelFriendRequest();
                    }
                    if (CURRENT_STATE.equals("request_received")) {
                        acceptFriendRequest();
                    }
                    if(CURRENT_STATE.equals("friends"))
                    {
                        UnfriendAfriend();
                    }

                }
            });
        }
       else {
           button_declineRequest.setVisibility(View.INVISIBLE);
           button_sendRequest.setVisibility(View.INVISIBLE);
       }


    }

    private void declineFriendRequest() {


        friendRequestRefference.child(sender_user_id)
                .child(receiver_user_id)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            friendRequestRefference.child(receiver_user_id)
                                    .child(sender_user_id)
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                button_sendRequest.setEnabled(true);
                                                CURRENT_STATE = "not_friends";
                                                button_sendRequest.setText("Send friend request");

                                                button_declineRequest.setVisibility(View.INVISIBLE);
                                                button_declineRequest.setEnabled(false);
                                            }}});
                        }
                    }
                });



    }

    private void UnfriendAfriend() {

        friendRefference.child(sender_user_id).child(receiver_user_id)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    friendRefference.child(receiver_user_id).child(sender_user_id)
                            .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            button_sendRequest.setEnabled(true);
                            CURRENT_STATE="not_friends";
                            button_sendRequest.setText("Send friend request");

                            button_declineRequest.setVisibility(View.INVISIBLE);
                            button_declineRequest.setEnabled(false);
                        }
                    });
                }
            }
        });
    }

    private void acceptFriendRequest() {

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        final String saveCurrentDate = currentDate.format(calForDate.getTime());
        friendRefference.child(sender_user_id).child(receiver_user_id).setValue(saveCurrentDate)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        friendRefference.child(receiver_user_id)
                                .child(sender_user_id).setValue(saveCurrentDate)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        friendRequestRefference.child(sender_user_id)
                                                .child(receiver_user_id)
                                                .removeValue()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {

                                                                    friendRequestRefference.child(receiver_user_id)
                                                                            .child(sender_user_id)
                                                                            .removeValue()
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                                    if (task.isSuccessful()) {
                                                                                        button_sendRequest.setEnabled(true);
                                                                                        CURRENT_STATE = "friends";
                                                                                        button_sendRequest.setText("Unfriend this person");


                                                                                        button_declineRequest.setVisibility(View.INVISIBLE);
                                                                                        button_declineRequest.setEnabled(false);

                                                                                    }
                                                                                }
                                                                            });
                                                                }
                                                        }
                                                    });
                                        }
                                    });
                        }
                    });

    }

    private void cancelFriendRequest() {

        friendRequestRefference.child(sender_user_id)
                .child(receiver_user_id)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            friendRequestRefference.child(receiver_user_id)
                                    .child(sender_user_id)
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                button_sendRequest.setEnabled(true);
                                                CURRENT_STATE = "not_friends";
                                                button_sendRequest.setText("Send friend request");

                                                button_declineRequest.setVisibility(View.INVISIBLE);
                                                button_declineRequest.setEnabled(false);
                              }}});
                        }
                    }
                });
    }

    private void sendFriendRequestToAUeser() {

        friendRequestRefference.child(sender_user_id).child(receiver_user_id)
                .child("request_type")
                .setValue("sent")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            friendRequestRefference.child(receiver_user_id).child(sender_user_id)
                                    .child("request_type").setValue("received")
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {


                                                HashMap<String,String>notifications=new HashMap<String,String>();

                                                notifications.put("from",sender_user_id);
                                                notifications.put("type","request");

                                                notificationRefference.child(receiver_user_id)
                                                        .push().setValue(notifications)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if(task.isSuccessful())
                                                                {
                                                                    button_sendRequest.setEnabled(true);
                                                                    CURRENT_STATE = "request_sent";
                                                                    button_sendRequest.setText("Cancel friend request");

                                                                    button_declineRequest.setVisibility(View.INVISIBLE);
                                                                    button_declineRequest.setEnabled(false);
                                                                }
                                                                else
                                                                {
                                                                    Toast.makeText(ProfileActivity.this,"Notification failed",Toast.LENGTH_SHORT).show();
                                                                }

                                                            }
                                                        });
                                            }
                                        }
                                    });
                        }
                    }
                });
    }
}
