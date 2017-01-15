package com.rodrigobresan.githubmvvm;

import android.app.Application;
import android.content.Context;

import com.rodrigobresan.githubmvvm.data.GithubFactory;
import com.rodrigobresan.githubmvvm.data.GithubService;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class GithubApplication extends Application {

    private GithubService githubService;
    private Scheduler scheduler;

    private static GithubApplication get(Context context) {
        return (GithubApplication) context.getApplicationContext();
    }

    public static GithubApplication create(Context context) {
        return GithubApplication.get(context);
    }

    public GithubService getGithubService() {
        if (githubService == null) {
            githubService = GithubFactory.create();
        }

        return githubService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setGithubService(GithubService githubService) {
        this.githubService = githubService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
