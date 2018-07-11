package com.example.lenovo.retrofitfetchingjsonfromurl;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lenovo on 1/11/2018.
 */

public interface Api {

    String BASE_URL="https://simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<Hero>>getHerosData();

}
