package com.nextstacks.sharedprefernce.api;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {


    @GET("everything/")
    Call<String> getAllNews(@QueryMap HashMap<String, Object> queries);

//    @GET("everything/")
//    Call<String> getAllNews(@Query("q") String q, @Query("apiKey") String apiKey, @Query("sortBy") String sortBy);


}
