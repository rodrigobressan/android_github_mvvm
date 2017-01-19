package com.rodrigobresan.githubmvvm.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rodrigobresan.githubmvvm.R;
import com.rodrigobresan.githubmvvm.databinding.RepositoriesActivityBinding;
import com.rodrigobresan.githubmvvm.model.Repository;
import com.rodrigobresan.githubmvvm.viewmodel.RepositoryViewModel;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import java.util.List;


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setSupportActionBar(repositoriesActivityBinding.toolbarRepositories);
        setupListRepository(repositoriesActivityBinding.recyclerRepository);
    }

    private void initDataBinding() {
        repositoriesActivityBinding = DataBindingUtil.setContentView(this, R.layout.repositories_activity);
        repositoryViewModel = new RepositoryViewModel(mainView, getContext());
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
}

