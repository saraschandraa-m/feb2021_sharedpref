package com.nextstacks.sharedprefernce.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsData {

    public String status;
    public int totalResults;
    public ArrayList<Article> articleList;

    public static NewsData parseResponse(JSONObject jsonObject) {
        NewsData item = new NewsData();
        item.status = jsonObject.optString("status");
        item.totalResults = jsonObject.optInt("totalResults");

        item.articleList = new ArrayList<>();

        JSONArray articlesArray = jsonObject.optJSONArray("articles");

        if (articlesArray != null && articlesArray.length() > 0) {
            for (int i = 0; i < articlesArray.length(); i++) {
                Article article = Article.parseResponse(articlesArray.optJSONObject(i));
                item.articleList.add(article);
            }
        }
        return item;

    }
}
