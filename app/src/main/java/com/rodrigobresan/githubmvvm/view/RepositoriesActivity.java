package com.rodrigobresan.githubmvvm.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.rodrigobresan.githubmvvm.GithubApplication;
import com.rodrigobresan.githubmvvm.R;
import com.rodrigobresan.githubmvvm.data.GithubApi;
import com.rodrigobresan.githubmvvm.databinding.RepositoriesActivityBinding;
import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.viewmodel.RepositoryViewModel;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import rx.Scheduler;


/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class RepositoriesActivity extends AppCompatActivity implements RepositoryViewModelContract.MainView {

    private RepositoriesActivityBinding viewBinding;

    RepositoryViewModel repositoryViewModel;

    @Inject
    GithubApi githubApi;

    @Inject
    Scheduler scheduler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GithubApplication.get(getContext()).getAppComponent().inject(this);

        initDataBinding();
        setSupportActionBar(viewBinding.toolbarRepositories);
        setupListRepository(viewBinding.recyclerRepository);
    }


    private void initDataBinding() {
        viewBinding = DataBindingUtil.setContentView(this, R.layout.repositories_activity);
        repositoryViewModel = new RepositoryViewModel(this, githubApi, scheduler);
        viewBinding.setRepositoryViewModel(repositoryViewModel);
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
        RepositoryAdapter repositoryAdapter = (RepositoryAdapter) viewBinding.recyclerRepository.getAdapter();
        repositoryAdapter.setRepositoryList(repositoryList);
    }

    public void setupListRepository(RecyclerView recyclerRepository) {
        RepositoryAdapter repositoryAdapter = new RepositoryAdapter(LayoutInflater.from(this));
        recyclerRepository.setAdapter(repositoryAdapter);
        recyclerRepository.setLayoutManager(new LinearLayoutManager(this));
    }

}

