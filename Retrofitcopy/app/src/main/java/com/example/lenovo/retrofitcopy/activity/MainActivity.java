package com.example.lenovo.retrofitcopy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lenovo.retrofitcopy.R;
import com.example.lenovo.retrofitcopy.model.User;
import com.example.lenovo.retrofitcopy.network.ApiClient;
import com.example.lenovo.retrofitcopy.recycleAdopter.RecycleAdopter;
import com.example.lenovo.retrofitcopy.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserDataList();
    }

    private void getUserDataList() {

        try {

            ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
            Call<List<User>> call = apiService.getUserData();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    Log.d("noRespone", response.message());
                    List<User> userList = response.body();
                    linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    RecyclerView recyclerView = findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    RecycleAdopter recycleAdopter = new RecycleAdopter(userList);
                    recyclerView.setAdapter(recycleAdopter);
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });
        }catch (Exception e)
        {

        }
    }
}
