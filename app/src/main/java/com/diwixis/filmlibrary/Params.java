package com.diwixis.filmlibrary;

import java.util.HashMap;

/**
 * Created by Diwixis on 19.04.2017.
 */

public class Params {
    public static HashMap<String, String> getMovieParams(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("api_key", Constants.apiKey);
        hashMap.put("language", "en");
        hashMap.put("page", "1");
        return hashMap;
    }
}
