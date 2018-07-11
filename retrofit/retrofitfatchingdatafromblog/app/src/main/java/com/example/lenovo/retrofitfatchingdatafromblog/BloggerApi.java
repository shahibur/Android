package com.example.lenovo.retrofitfatchingdatafromblog;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lenovo on 1/14/2018.
 */

public class BloggerApi {


    public static final String KEY="AIzaSyAsPQJSkXRTnVJUxmCTaDnEwAXhLv3pAms";

    public static final String BASE_URL="https://www.googleapis.com/blogger/v3/blogs/4367375316625933561/";

    public static PostService postService=null;

    public static PostService getPostService()
    {
        if(postService==null)
        {
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService=retrofit.create(PostService.class);
        }

        return postService;
    }

    public interface PostService
    {

        @GET("?key="+KEY)
        Call<PostList>getPostlist();

         // @GET("{postId}/?key="+KEY)
        // Call<Posts>getPostById(@Path("postId") String id);

    }
}
