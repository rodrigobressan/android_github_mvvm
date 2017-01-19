package com.rodrigobresan.githubmvvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.rodrigobresan.githubmvvm.model.Repository;
import com.rodrigobresan.githubmvvm.view.RepositoryDetailActivity;

/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class ItemRepositoryViewModel extends BaseObservable {

    private Repository repository;
    private Context context;

    public ItemRepositoryViewModel(Repository repository, Context context) {
        this.repository = repository;
        this.context = context;
    }

    public String getName() {
        return repository.name;
    }

    public String getDescription() {
        return repository.description;
    }

    public void onItemClick(View view) {
        context.startActivity(RepositoryDetailActivity.launchDetail(view.getContext(), repository));
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
        notifyChange();
    }
}
