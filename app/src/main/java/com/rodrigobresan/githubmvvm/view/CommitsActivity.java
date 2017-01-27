package com.rodrigobresan.githubmvvm.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rodrigobresan.githubmvvm.R;
import com.rodrigobresan.githubmvvm.databinding.CommitsActivityBinding;
import com.rodrigobresan.githubmvvm.model.Commit;
import com.rodrigobresan.githubmvvm.model.Repository;
import com.rodrigobresan.githubmvvm.viewmodel.CommitsViewModel;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.CommitViewModelContract;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import java.util.List;

/**
 * Created by rodrigobresan on 1/19/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */
public class CommitsActivity extends AppCompatActivity
        implements CommitViewModelContract.DetailView {

    private static final String EXTRA_REPOSITORY = "extra_repository";
    private CommitsActivityBinding commitBinding;
    private CommitsViewModel commitsViewModel;
    private Repository repository;
    private CommitViewModelContract.DetailView commitsView = this;

    public static Intent launchDetail(Context context, Repository repository) {
        Intent intent = new Intent(context, CommitsActivity.class);
        intent.putExtra(EXTRA_REPOSITORY, repository);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtrasFromIntent();
        initDataBinding();
        setUpListCommits(commitBinding.recyclerCommits);
    }

    private void setUpListCommits(RecyclerView recyclerCommits) {
        CommitsAdapter commitsAdapter = new CommitsAdapter();
        recyclerCommits.setAdapter(commitsAdapter);
        recyclerCommits.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initDataBinding() {
        commitBinding = DataBindingUtil.setContentView(this, R.layout.commits_activity);
        commitsViewModel = new CommitsViewModel(commitsView, this, repository);
        commitBinding.setRepositoryViewModel(commitsViewModel);
    }

    @Override
    public Context getContext() {
        return CommitsActivity.this;
    }

    @Override
    public void loadCommits(List<Commit> commitList) {
        CommitsAdapter commitsAdapter = (CommitsAdapter) this.commitBinding.recyclerCommits.getAdapter();
        commitsAdapter.setCommitList(commitList);
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(), "Error while loading", Toast.LENGTH_SHORT).show();
    }

    public void getExtrasFromIntent() {
        this.repository = (Repository) getIntent().getSerializableExtra(EXTRA_REPOSITORY);
    }
}
