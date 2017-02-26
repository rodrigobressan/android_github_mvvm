package com.rodrigobresan.githubmvvm.di.component;

import com.rodrigobresan.githubmvvm.di.module.AppModule;
import com.rodrigobresan.githubmvvm.di.module.NetModule;
import com.rodrigobresan.githubmvvm.view.CommitsActivity;
import com.rodrigobresan.githubmvvm.view.RepositoriesActivity;
import com.rodrigobresan.githubmvvm.viewmodel.CommitsViewModel;
import com.rodrigobresan.githubmvvm.viewmodel.RepositoryViewModel;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * Created by rodrigobresan on 2/25/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(CommitsViewModel commitsViewModel);
    void inject(RepositoryViewModel repositoryViewModel);

    void inject(RepositoriesActivity repositoriesActivity);
}
