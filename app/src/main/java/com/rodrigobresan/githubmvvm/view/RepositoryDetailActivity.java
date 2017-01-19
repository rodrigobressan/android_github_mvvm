package com.rodrigobresan.githubmvvm.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rodrigobresan.githubmvvm.R;
import com.rodrigobresan.githubmvvm.databinding.RepositoryDetailActivityBinding;
import com.rodrigobresan.githubmvvm.model.Repository;
import com.rodrigobresan.githubmvvm.viewmodel.RepositoryDetailViewModel;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryDetailViewModelContract;

/**
 * Created by rodrigobresan on 1/19/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */
public class RepositoryDetailActivity extends AppCompatActivity
        implements RepositoryDetailViewModelContract.DetailView {

    private static final String EXTRA_REPOSITORY = "extra_repository";
    private RepositoryDetailActivityBinding repositoryDetailBinding;
    private Repository repository;

    public static Intent launchDetail(Context context, Repository repository) {
        Intent intent = new Intent(context, RepositoryDetailActivity.class);
        intent.putExtra(EXTRA_REPOSITORY, repository);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repositoryDetailBinding = DataBindingUtil.setContentView(this, R.layout.repository_detail_activity);

        getExtrasFromIntent();
        setUpViewModel();
    }

    @Override
    public Context getContext() {
        return RepositoryDetailActivity.this;
    }

    public void getExtrasFromIntent() {
        this.repository = (Repository) getIntent().getSerializableExtra(EXTRA_REPOSITORY);
    }

    private void setUpViewModel() {
        RepositoryDetailViewModel repositoryDetailViewModel = new RepositoryDetailViewModel(repository);
        repositoryDetailBinding.setRepositoryViewModel(repositoryDetailViewModel);
    }
}
