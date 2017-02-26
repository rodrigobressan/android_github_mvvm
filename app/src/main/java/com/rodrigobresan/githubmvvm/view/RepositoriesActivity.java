package com.rodrigobresan.githubmvvm.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rodrigobresan.githubmvvm.GithubApplication;
import com.rodrigobresan.githubmvvm.R;
import com.rodrigobresan.githubmvvm.data.GithubService;
import com.rodrigobresan.githubmvvm.databinding.RepositoriesActivityBinding;
import com.rodrigobresan.githubmvvm.di.component.NetComponent;
import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.viewmodel.RepositoryViewModel;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;


/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class RepositoriesActivity extends AppCompatActivity implements RepositoryViewModelContract.MainView {

    private RepositoriesActivityBinding repositoriesActivityBinding;
    private RepositoryViewModel repositoryViewModel;
    private RepositoryViewModelContract.MainView mainView = this;

    @Inject
    GithubService githubService;

    @Inject
    Scheduler scheduler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setSupportActionBar(repositoriesActivityBinding.toolbarRepositories);
        setupListRepository(repositoriesActivityBinding.recyclerRepository);
    }

    private void initDataBinding() {
        NetComponent netComponent = ((GithubApplication) getApplication()).getNetComponent();

        netComponent.inject(this);

        repositoriesActivityBinding = DataBindingUtil.setContentView(this, R.layout.repositories_activity);
        repositoryViewModel = new RepositoryViewModel(mainView, githubService, scheduler);
        repositoriesActivityBinding.setRepositoryViewModel(repositoryViewModel);
    }

    @Override
    public Context getContext() {
        return RepositoriesActivity.this;
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(), "Error when loading data", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repositoryViewModel.destroy();
    }

    @Override
    public void loadData(List<Repository> repositoryList) {
        RepositoryAdapter repositoryAdapter = (RepositoryAdapter) repositoriesActivityBinding.recyclerRepository.getAdapter();
        repositoryAdapter.setRepositoryList(repositoryList);
    }

    public void setupListRepository(RecyclerView recyclerRepository) {
        RepositoryAdapter repositoryAdapter = new RepositoryAdapter();
        recyclerRepository.setAdapter(repositoryAdapter);
        recyclerRepository.setLayoutManager(new LinearLayoutManager(this));
    }

    @Subcomponent(modules = RepositoriesActivityModule.class)
    public interface RepositoriesActivityComponent {
        void injecT(@NonNull RepositoriesActivity repositoriesActivity);
    }

    @Module
    public static class RepositoriesActivityModule {

        @Provides
        @NonNull
        public RepositoryViewModel provideRepositoryViewModel(@NonNull RepositoryViewModelContract.MainView mainView,
                                                              @NonNull GithubService githubService,
                                                              @NonNull Scheduler scheduler) {

            return new RepositoryViewModel(mainView, githubService, scheduler);
        }
    }
}

