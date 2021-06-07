package com.nextstacks.sharedprefernce.api;

import org.json.JSONObject;

public class Article {

    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String publishedAt;
    public String content;
    public Source source;


    public static Article parseResponse(JSONObject jsonObject) {
        Article item = new Article();

        item.author = jsonObject.optString("author");
        item.title = jsonObject.optString("title");
        item.description = jsonObject.optString("description");
        item.url = jsonObject.optString("url");
        item.urlToImage = jsonObject.optString("urlToImage");
        item.publishedAt = jsonObject.optString("publishedAt");
        item.content = jsonObject.optString("content");
        item.source = Source.parseResponse(jsonObject.optJSONObject("source"));
        return item;
    }

}
