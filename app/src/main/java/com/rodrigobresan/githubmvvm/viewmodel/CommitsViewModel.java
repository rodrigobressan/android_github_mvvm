package com.rodrigobresan.githubmvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.rodrigobresan.githubmvvm.GithubApplication;
import com.rodrigobresan.githubmvvm.data.GithubService;
import com.rodrigobresan.githubmvvm.model.Repository;
import com.rodrigobresan.githubmvvm.model.Commit;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.CommitViewModelContract;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by rodrigobresan on 1/19/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class CommitsViewModel implements CommitViewModelContract.ViewModel {

    public ObservableInt detailsProgress;
    public ObservableInt commitsList;

    private Repository repository;
    private CommitViewModelContract.DetailView detailView;

    private Context context;
    private Subscription subscription;

    public CommitsViewModel(@NonNull CommitViewModelContract.DetailView detailView,
                            @NonNull Context context,
                            @NonNull Repository repository) {
        this.repository = repository;
        this.detailView = detailView;
        this.context = context;

        detailsProgress = new ObservableInt(View.GONE);
        commitsList = new ObservableInt(View.GONE);

        initializeViews();
        fetchCommits();
    }

    private void initializeViews() {
        detailsProgress.set(View.GONE);
        commitsList.set(View.GONE);
    }

    private void fetchCommits() {
        unSubscribeFromObservable();

        GithubApplication githubApplication = GithubApplication.create(context);
        GithubService githubService = githubApplication.getGithubService();

        subscription = githubService.fetchRepositoryDetail(repository.repositoryOwner.login, repository.name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(githubApplication.subscribeScheduler())
                .subscribe(new Action1<List<Commit>>() {
                    @Override
                    public void call(List<Commit> commitList) {
                        // update UI observables

                        if (detailView != null) {
                            detailView.loadCommits(commitList);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        detailsProgress.set(View.GONE);
                        commitsList.set(View.GONE);
                        // log somewhere (crashlytics)

                        if (detailView != null) {
                            detailView.displayError();
                        }
                    }
                });
    }

    private void unSubscribeFromObservable() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public String getRepositoryName() {
        return repository.name;
    }

    public String getRepositoryDescription() {
        return repository.description;
    }

    @Override
    public void destroy() {
        unSubscribeFromObservable();
        detailView = null;
        context = null;
    }
}
