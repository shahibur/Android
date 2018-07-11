package com.example.lenovo.retrofitfetchingdatafromgithubrepogitory.api.service;

import android.test.suitebuilder.annotation.LargeTest;

import com.example.lenovo.retrofitfetchingdatafromgithubrepogitory.api.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lenovo on 1/9/2018.
 */

public interface GitHubClient {


    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>>reposForUser(@Path("user") String user);

}
