package com.example.lenovo.retrofitfetchingdatafromgithubrepogitory.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.retrofitfetchingdatafromgithubrepogitory.R;
import com.example.lenovo.retrofitfetchingdatafromgithubrepogitory.api.model.GitHubRepo;
import com.example.lenovo.retrofitfetchingdatafromgithubrepogitory.api.service.GitHubClient;
import com.example.lenovo.retrofitfetchingdatafromgithubrepogitory.ui.adapter.GitHubRepoAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    ////    https://api.github.com//users/{user}/reposfs-opensource
        listView=findViewById(R.id.listView);

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit=builder.build();

        GitHubClient gitHubClient=retrofit.create(GitHubClient.class);
        Call<List<GitHubRepo>>call=gitHubClient.reposForUser("fs-opensource");

        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {

                List<GitHubRepo>userRepo=response.body();

                listView.setAdapter(new GitHubRepoAdapter(MainActivity.this,userRepo));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
