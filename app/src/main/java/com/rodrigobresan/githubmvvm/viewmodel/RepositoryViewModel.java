package com.rodrigobresan.githubmvvm.viewmodel;

import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.rodrigobresan.githubmvvm.data.GithubService;
import com.rodrigobresan.githubmvvm.di.component.NetComponent;
import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import java.util.List;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class RepositoryViewModel implements RepositoryViewModelContract.ViewModel {

    public ObservableInt repositoryProgress;
    public ObservableInt repositoryList;

    @NonNull
    private RepositoryViewModelContract.MainView mainView;

    @NonNull
    private Subscription subscription;

    @NonNull
    GithubService githubService;

    @NonNull
    Scheduler scheduler;

    public RepositoryViewModel(@NonNull RepositoryViewModelContract.MainView mainView,
                               @NonNull GithubService githubService,
                               @NonNull Scheduler scheduler) {

        this.mainView = mainView;
        this.githubService = githubService;
        this.scheduler = scheduler;

        repositoryProgress = new ObservableInt(View.GONE);
        repositoryList = new ObservableInt(View.GONE);
    }

    public void onClickFabLoad(View view) {
        initializeViews();
        fetchRepositoryList();
    }

    private void initializeViews() {
        repositoryProgress.set(View.GONE);
        repositoryList.set(View.GONE);
    }

    private void fetchRepositoryList() {
        unSubscribeFromObservable();

        subscription = githubService.fetchRepositories("bresan")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
                .subscribe(new Action1<List<Repository>>() {
                    @Override
                    public void call(List<Repository> listRepositoriesResponse) {
                        repositoryProgress.set(View.GONE);
                        repositoryList.set(View.VISIBLE);

                        if (mainView != null) {
                            mainView.loadData(listRepositoriesResponse);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();

                        repositoryProgress.set(View.GONE);
                        repositoryList.set(View.GONE);

                        if (mainView != null) {
                            mainView.displayError();
                        }
                    }
                });

    }


    private void unSubscribeFromObservable() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void destroy() {
        unSubscribeFromObservable();
        mainView = null;
    }
}
