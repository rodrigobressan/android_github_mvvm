package com.rodrigobresan.githubmvvm.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rodrigobresan.githubmvvm.R;
import com.rodrigobresan.githubmvvm.model.entities.Repository;

/**
 * Created by rodrigobresan on 3/8/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class DetailedRepositoryActivity extends AppCompatActivity {

    private static final String EXTRA_REPOSITORY = "args.extra_repositoryy";

    public static void start(Context context, Repository repository) {
        Intent intent = new Intent(context, DetailedRepositoryActivity.class);
        intent.putExtra(EXTRA_REPOSITORY, repository);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detaill_repository);
    }
}
