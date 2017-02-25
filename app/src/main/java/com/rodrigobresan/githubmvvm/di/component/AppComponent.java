package com.rodrigobresan.githubmvvm.di.component;

import com.rodrigobresan.githubmvvm.view.CommitsActivity;

import dagger.Component;

/**
 * Created by rodrigobresan on 2/25/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

@Component
public interface AppComponent {
    void inject(CommitsActivity activity);
}
