package com.rodrigobresan.githubmvvm.di.module;

import android.app.Application;

import com.rodrigobresan.githubmvvm.util.CustomImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rodrigobresan on 2/25/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    /**
     * Provides the current application
     * @return the current application
     */
    @Provides
    @Singleton
    Application providesApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    CustomImageLoader providesCustomImageLoader(Application application) {
        return new CustomImageLoader(application);
    }

}

