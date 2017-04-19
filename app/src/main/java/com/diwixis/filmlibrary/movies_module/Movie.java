package com.diwixis.filmlibrary.movies_module;

/**
 * Created by Diwixis on 18.04.2017.
 */

class Movie {
    String URL;

    public Movie(String URL) {
        this.URL = URL;
    }

    public String getImageUrl(){
        return URL;
    }
}
