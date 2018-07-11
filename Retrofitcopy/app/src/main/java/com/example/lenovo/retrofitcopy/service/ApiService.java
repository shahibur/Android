package com.example.lenovo.retrofitcopy.service;

import com.example.lenovo.retrofitcopy.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lenovo on 1/7/2018.
 */

public interface ApiService {

    @GET("json_bangla")
    Call<List<User>>getUserData();
}
