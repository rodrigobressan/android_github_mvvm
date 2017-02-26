package com.rodrigobresan.githubmvvm;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.rodrigobresan.githubmvvm.data.GithubApi;
import com.rodrigobresan.githubmvvm.di.component.ApplicationComponent;
import com.rodrigobresan.githubmvvm.di.component.DaggerApplicationComponent;
import com.rodrigobresan.githubmvvm.di.module.ApiModule;
import com.rodrigobresan.githubmvvm.di.module.AppModule;
import com.rodrigobresan.githubmvvm.di.module.NetModule;


/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class GithubApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = prepareApplicationComponent().build();
    }

    @NonNull
    protected DaggerApplicationComponent.Builder prepareApplicationComponent() {
        return DaggerApplicationComponent.builder()
                        .appModule(new AppModule(this))
                        .netModule(new NetModule())
                        .apiModule(new ApiModule(getResources().getString(R.string.github_base_url)));
    }

    @NonNull
    public ApplicationComponent getAppComponent() {
        return applicationComponent;
    }

    // Prevent need in a singleton (global) reference to the application object
    @NonNull
    public static GithubApplication get(@NonNull Context context) {
        return (GithubApplication) context.getApplicationContext();
    }
}
