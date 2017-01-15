package com.rodrigobresan.githubmvvm.data;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class GithubFactory {

    private static final String GITHUB_API_URL = "https://api.github.com";

    public static GithubService create() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(initLoggingInterceptor()).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(GithubService.class);
    }


    private static HttpLoggingInterceptor initLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return logging;
    }


}
