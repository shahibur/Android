package com.example.lenovo.retrofitpractice01.service;

import com.example.lenovo.retrofitpractice01.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lenovo on 1/7/2018.
 */

public interface APIservice {

        @GET("json_bangla")
        Call<List<User>> getUserData();

}
