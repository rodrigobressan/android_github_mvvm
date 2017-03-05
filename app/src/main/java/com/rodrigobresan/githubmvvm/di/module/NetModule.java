package com.rodrigobresan.githubmvvm.di.module;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.rodrigobresan.githubmvvm.other.EntityTypeAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by rodrigobresan on 2/25/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

@Module
public class NetModule {

    /**
     * Values to be used on our OkHttpCache
     */
    private static final int SIZE_MEGABYTE = 1024 * 1024;
    private static final int CACHE_SIZE = 10 * SIZE_MEGABYTE;

    /**
     * Provides the instance of our HttpCache.
     * @param application the object of our application, used for retrieve the cache directory
     * @return the OkHttpCache instance
     */
    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        Cache cache = new Cache(application.getCacheDir(), CACHE_SIZE);
        return cache;
    }

    /**
     * Provides one instance of our TypeAdapterFactory. We need to do it since we are using
     * AutoValue library
     * @return the instance of the {@link TypeAdapterFactory}
     */
    @Provides
    @NonNull
    @Singleton
    public TypeAdapterFactory provideTypeAdapterFactory() {
        return EntityTypeAdapterFactory.create();
    }

    /**
     * Provides our Gson object in order to deserialize our objects received through OkHttpClient
     * @param typeAdapterFactory the adapter factory used for deserialize our objects
     * @return the instance of our Gson object
     */
    @Provides
    @Singleton
    Gson provideGson(TypeAdapterFactory typeAdapterFactory) {

        return new GsonBuilder()
                .registerTypeAdapterFactory(typeAdapterFactory)
                .create();
    }

    /**
     * Provides our Http Logging Interceptor that will print all our requests into log cat
     * @return instance of the HttpLoggingInterceptor
     */
    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return logging;
    }

    /**
     * Provides our Http Client
     * @param cache cache used for the requests
     * @param loggingInterceptor logging interceptor
     * @return instance of the OkHttpClient
     */
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor);

        return clientBuilder.build();
    }

    /**
     * Provides our subscriber for usage with RxJava
     * @return
     */
    @Provides
    @Singleton
    Scheduler providesSubscriber() {
        return Schedulers.io();
    }
}
