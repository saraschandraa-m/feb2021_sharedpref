package com.nextstacks.sharedprefernce.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.nextstacks.sharedprefernce.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity implements CategoryListAdapter.CategorySelectListener {


    private RecyclerView mRcCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        mRcCategories = findViewById(R.id.rc_categories);
        mRcCategories.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


        CategoryListAdapter adapter = new CategoryListAdapter(this);
        adapter.setListener(this);
        mRcCategories.setAdapter(adapter);

        getNews();
    }


    private void getNews() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


        HashMap<String, Object> queries = new HashMap<>();
        queries.put("apiKey", "4c82d7e8131841f484c6cf169bb83ae4");
        queries.put("sources", "google-news");

        Call<String> getAllNews = apiInterface.getAllNews(queries);

        getAllNews.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("GetNews", "Success");
                Log.i("GetNews", response.body());
                try {
                    JSONObject responseObject = new JSONObject(response.body());
                    NewsData data = NewsData.parseResponse(responseObject);

                    Toast.makeText(NewsActivity.this, data.articleList.size() + " data available", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("GetNews", "Failed");
                Log.i("GetNews", t.getMessage());
            }
        });

    }


    private void getCategoryNews(String category) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


        HashMap<String, Object> queries = new HashMap<>();
        queries.put("apiKey", "4c82d7e8131841f484c6cf169bb83ae4");
        queries.put("category", category);

        Call<String> getTopHeadles = apiInterface.getTopHeadLines(queries);

        getTopHeadles.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("GetNews_Category", "Success");
                Log.i("GetNews_Category", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    @Override
    public void onCategorySelected(String category) {
        getCategoryNews(category);
    }
}