package com.rodrigobresan.githubmvvm;

import android.app.Application;

import com.rodrigobresan.githubmvvm.di.component.DaggerNetComponent;
import com.rodrigobresan.githubmvvm.di.component.NetComponent;
import com.rodrigobresan.githubmvvm.di.module.AppModule;
import com.rodrigobresan.githubmvvm.di.module.NetModule;


/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class GithubApplication extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeNetComponent();
    }

    private void initializeNetComponent() {
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(getResources().getString(R.string.github_base_url)))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
