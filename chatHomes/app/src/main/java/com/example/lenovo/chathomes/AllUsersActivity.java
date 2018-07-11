package com.example.lenovo.chathomes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.mychat.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllUsersActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mtoolbar;
    private RecyclerView allUserList;
    private DatabaseReference allUserDatabaseRefference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);


        mtoolbar=findViewById(R.id.all_user_app_bar);

        try {
            setSupportActionBar(mtoolbar);
            getSupportActionBar().setTitle("All users");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        allUserDatabaseRefference= FirebaseDatabase.getInstance().getReference().child("Users");
        allUserDatabaseRefference.keepSynced(true);

        allUserList=findViewById(R.id.recyclerView_all_userList);
        allUserList.setHasFixedSize(true);
        allUserList.setLayoutManager(new LinearLayoutManager(this));






    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<AllUser,AllUserHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<AllUser, AllUserHolder>
                        (
                                AllUser.class,
                                R.layout.all_user_display_layout,
                                AllUserHolder.class,
                                allUserDatabaseRefference

                        )
                {
                    @Override
                    protected void populateViewHolder(AllUserHolder viewHolder, AllUser model, final int position) {


                        viewHolder.setUser_name(model.getUser_name());
                        viewHolder.setUser_status(model.getUser_status());
                        viewHolder.setUser_thumbs_image(getApplicationContext(),model.getUser_thumbs_image());

                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String visit_user_id=getRef(position).getKey();
                                Intent profileIntent=new Intent(AllUsersActivity.this,ProfileActivity.class
                                );
                                profileIntent.putExtra("visit_user_id",visit_user_id);
                                startActivity(profileIntent);

                            }
                        });

                    }
                };

        allUserList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class AllUserHolder extends RecyclerView.ViewHolder
    {
        View mView;

        public AllUserHolder(View itemView) {
            super(itemView);

            mView=itemView;
        }

        public void setUser_name(String user_name) {

            TextView userName=mView.findViewById(R.id.all_user_display_userName);
            userName.setText(user_name);
        }

        public void setUser_status(String user_status) {

            TextView userStatus=mView.findViewById(R.id.all_user_display_userStatus);
            userStatus.setText(user_status);

        }
        public void setUser_thumbs_image(final Context cntx,final String user_thumbs_image) {

           final CircleImageView thumb_imageView=mView.findViewById(R.id.all_user_profile_image);
        //    Picasso.with(cntx).load(user_thumbs_image).placeholder(R.drawable.default_profile).into(thumb_imageView);

            Picasso.with(cntx).load(user_thumbs_image).networkPolicy(NetworkPolicy.OFFLINE).
                    placeholder(R.drawable.default_profile)
                    .into(thumb_imageView, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                            Picasso.with(cntx).load(user_thumbs_image).placeholder(R.drawable.default_profile).into(thumb_imageView);

                        }
                    });
        }
    }
}
