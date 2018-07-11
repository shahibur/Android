package com.example.lenovo.retrofitfatchingdatafromblog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPostListData();

    }


    void getPostListData()
    {
        Call<PostList>call=BloggerApi.getPostService().getPostlist();

        call.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList postList=response.body();
                Toast.makeText(MainActivity.this,"succeess",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(MainActivity.this,"not succeess",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
