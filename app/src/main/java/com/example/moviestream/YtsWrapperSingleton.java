package com.example.moviestream;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class YtsWrapperSingleton {
    private final String apiMovieList="https://yts.mx/api/v2/list_movies.json?sort=seeds&limit=20&page=";
    private String genre;

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void get(int page, OnYtsFetch callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject response=get(page);
                    callback.onSuccess(response);
                }catch (Exception e){
                    callback.onFailed(e);
                }
            }
        }).start();
    }
    private JSONObject get(int page) throws IOException, JSONException {
        String movieListLink=apiMovieList+page;//+"&genre="+getGenre();
        URL url=new URL(movieListLink);
        HttpsURLConnection httpsURLConnection= (HttpsURLConnection) url.openConnection();
        StringBuilder json=new StringBuilder();
        byte []buff=new byte[1024];
        InputStream response=httpsURLConnection.getInputStream();
        int l;
        while((l=response.read(buff))>0){
            String r=new String(buff,0,l);
            json.append(r);
        }
        String ytsResponse=json.toString();
        return new JSONObject(ytsResponse);

    }
    private static YtsWrapperSingleton instance;
    public static YtsWrapperSingleton getInstance(){
        if(instance==null) instance=new YtsWrapperSingleton();
        return instance;
    }
}
