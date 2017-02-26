package com.rodrigobresan.githubmvvm.di.component;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.rodrigobresan.githubmvvm.data.GithubApi;
import com.rodrigobresan.githubmvvm.di.module.ApiModule;
import com.rodrigobresan.githubmvvm.di.module.AppModule;
import com.rodrigobresan.githubmvvm.di.module.NetModule;
import com.rodrigobresan.githubmvvm.view.RepositoriesActivity;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rodrigobresan on 2/26/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class,
        NetModule.class
})
public interface ApplicationComponent {
    @NonNull
    Gson gson();

    @NonNull
    GithubApi githubApi();

    void inject(@NonNull RepositoriesActivity repositoriesActivity);

}
