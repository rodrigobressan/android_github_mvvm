package com.rodrigobresan.githubmvvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.rodrigobresan.githubmvvm.model.Repository;

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
        //TODO open detail of repo
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
        notifyChange();
    }
}
