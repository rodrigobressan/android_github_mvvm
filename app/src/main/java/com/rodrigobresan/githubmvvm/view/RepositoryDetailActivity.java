package com.rodrigobresan.githubmvvm.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.rodrigobresan.githubmvvm.model.Repository;
import com.rodrigobresan.githubmvvm.viewmodel.RepositoryDetailViewModelContract;

/**
 * Created by rodrigobresan on 1/19/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */
public class RepositoryDetailActivity extends AppCompatActivity
        implements RepositoryDetailViewModelContract.DetailView {

    private static final String EXTRA_REPOSITORY = "extra_repository";

    public static Intent launchDetail(Context context, Repository repository) {
        Intent intent = new Intent(context, RepositoryDetailActivity.class);
        intent.putExtra(EXTRA_REPOSITORY, repository);

        return intent;
    }

    @Override
    public Context getContext() {
        return RepositoryDetailActivity.this;
    }
}
