package com.rodrigobresan.githubmvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.rodrigobresan.githubmvvm.GithubApplication;
import com.rodrigobresan.githubmvvm.data.GithubService;
import com.rodrigobresan.githubmvvm.model.Repository;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import java.util.List;

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

    private RepositoryViewModelContract.MainView mainView;

    private Context context;
    private Subscription subscription;

    public RepositoryViewModel(@NonNull RepositoryViewModelContract.MainView mainView,
                               @NonNull Context context) {
        this.mainView = mainView;
        this.context = context;

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

        GithubApplication githubApplication = GithubApplication.create(context);
        GithubService githubService = githubApplication.getGithubService();

        subscription = githubService.fetchRepositories("bresan")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(githubApplication.subscribeScheduler())
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
        context = null;
    }
}
