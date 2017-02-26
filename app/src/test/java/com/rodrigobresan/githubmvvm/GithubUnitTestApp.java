package com.rodrigobresan.githubmvvm;

import android.support.annotation.NonNull;

import com.rodrigobresan.githubmvvm.di.component.DaggerApplicationComponent;

/**
 * Created by rodrigobresan on 2/26/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class GithubUnitTestApp extends GithubApplication {

    @NonNull
    @Override
    protected DaggerApplicationComponent.Builder prepareApplicationComponent() {
        return super.prepareApplicationComponent();
    }

}
