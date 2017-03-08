package com.rodrigobresan.githubmvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rodrigobresan.githubmvvm.GithubApplication;
import com.rodrigobresan.githubmvvm.R;
import com.rodrigobresan.githubmvvm.data.GithubApi;
import com.rodrigobresan.githubmvvm.databinding.FragmentRepositoriesBinding;
import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.util.CustomImageLoader;
import com.rodrigobresan.githubmvvm.viewmodel.RepositoryViewModel;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import java.util.List;

import javax.inject.Inject;

import rx.Scheduler;

/**
 * Created by rodrigobresan on 3/1/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class RepositoriesFragment extends Fragment implements RepositoryViewModelContract.MainView {

    public static final String TAG = RepositoriesFragment.class.getCanonicalName();

    private static final String USER_TO_LOAD = "bresan";

    public RepositoryViewModel repositoryViewModel;

    @Inject
    GithubApi githubApi;

    @Inject
    Scheduler scheduler;

    @Inject
    CustomImageLoader imageLoader;

    FragmentRepositoriesBinding dataBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GithubApplication.get(getContext()).getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_repositories, container, false);
        return dataBinding.getRoot();
    }

    public void onFabClick() {
        repositoryViewModel.loadData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViewModel();
        initRepositoryList();
    }

    private void initRepositoryList() {
        RepositoryAdapter repositoryAdapter = new RepositoryAdapter(this, LayoutInflater.from(getContext()));
        dataBinding.recyclerRepository.setAdapter(repositoryAdapter);
        dataBinding.recyclerRepository.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(), "Error loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadData(List<Repository> repositoryList) {
        RepositoryAdapter repositoryAdapter = (RepositoryAdapter) dataBinding.recyclerRepository.getAdapter();
        repositoryAdapter.setRepositoryList(repositoryList);
    }

    @Override
    public void onItemSelected(Repository repository) {
        DetailedRepositoryActivity.start(getContext(), repository);
        Toast.makeText(getContext(), "Click on item: " + repository.name(), Toast.LENGTH_SHORT).show();
    }

    public void setViewModel() {
        repositoryViewModel = new RepositoryViewModel(USER_TO_LOAD, this, githubApi, scheduler, imageLoader);
    }

    @Override
    public void onResume() {
        super.onResume();
        repositoryViewModel.loadData();
    }

    @Override
    public void onDestroy() {
        repositoryViewModel.destroy();
        super.onDestroy();
    }
}

