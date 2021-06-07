package com.nextstacks.sharedprefernce.api;

import org.json.JSONException;
import org.json.JSONObject;

public class Source {

    public String id;
    public String name;


    public static Source parseResponse(JSONObject jsonObject) {
        Source item = new Source();
        item.id = jsonObject.optString("id");
        item.name = jsonObject.optString("name");
        return item;

    }
}
