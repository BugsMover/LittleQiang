package com.example.administrator.helloworld;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2017-11-09.
 */

public class JsonParse {
    public static List<NewsInfo> getNewsInfo(String json){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<NewsInfo>>(){}.getType();
        List<NewsInfo> newsInfos = gson.fromJson(json,listType);
        return newsInfos;
    }
}
