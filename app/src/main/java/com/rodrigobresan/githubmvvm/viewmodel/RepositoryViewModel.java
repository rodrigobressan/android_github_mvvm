package com.rodrigobresan.githubmvvm.viewmodel;

import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.rodrigobresan.githubmvvm.data.GithubApi;
import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.rx.MyRxBus;
import com.rodrigobresan.githubmvvm.util.CustomImageLoader;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
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
    private String user;

    @NonNull
    private RepositoryViewModelContract.MainView mainView;

    @NonNull
    private Subscription subscription;

    @NonNull
    private CustomImageLoader imageLoader;

    @NonNull
    private GithubApi githubApi;

    @NonNull
    private Scheduler scheduler;

    public RepositoryViewModel(@NonNull String user,
                               @NonNull RepositoryViewModelContract.MainView view,
                               @NonNull GithubApi githubApi,
                               @NonNull Scheduler scheduler,
                               @NonNull CustomImageLoader customImageLoader) {

        this.user = user;
        this.githubApi = githubApi;
        this.scheduler = scheduler;
        this.mainView = view;
        this.imageLoader = customImageLoader;

        initObservables();
        initializeViews();
        initFabListener();
    }

    private void initObservables() {
        repositoryProgress = new ObservableInt(View.GONE);
        repositoryList = new ObservableInt(View.GONE);
    }

    private void initializeViews() {
        repositoryProgress.set(View.GONE);
        repositoryList.set(View.GONE);
    }

    /**
     * This is our listener for any events dispatched from the Floating action button on the
     * Main Activity.
     */
    private void initFabListener() {
        Observable<Object> events = MyRxBus.getInstance().getEvents();

        events.subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                loadData();
            }
        });
    }

    private void fetchRepositoryList() {
        unSubscribeFromObservable();

        subscription = githubApi.fetchRepositories(user)
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

    @Override
    public void loadData() {
        fetchRepositoryList();
    }
}
