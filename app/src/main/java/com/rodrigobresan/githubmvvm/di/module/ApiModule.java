package com.rodrigobresan.githubmvvm.di.module;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.rodrigobresan.githubmvvm.BuildConfig;
import com.rodrigobresan.githubmvvm.data.GithubApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rodrigobresan on 2/26/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

@Module
public class ApiModule {

    @NonNull
    private final String baseUrl;

    public ApiModule(@NonNull String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * This method provides to us our GithubApi Service, used for making the requests to Github API
     * @param okHttpClient the client used for doing the HTTP requests
     * @param gson the gson used for converting our received data
     * @return the instance of our GithubApi
     */
    @Provides
    @Singleton
    GithubApi provideGitHubApi(@NonNull OkHttpClient okHttpClient,
                               @NonNull Gson gson) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .validateEagerly(BuildConfig.DEBUG)
                .build()
                .create(GithubApi.class);
    }
}
