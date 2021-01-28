package com.example.stuntingapps;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //Wifi
    private static final String BASE_URL = "http://12.12.12.225:8080/stunting-apps/public/";
    //Hotspot XL
    //private static final String BASE_URL = "http://192.168.43.43:8080/stunting-apps/public/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient () {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
