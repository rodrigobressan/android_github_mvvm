package com.rodrigobresan.githubmvvm.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.view.View;

import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.util.CustomImageLoader;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class ItemRepositoryViewModel extends BaseObservable {

    private final RepositoryViewModelContract.MainView view;

    private Repository repository;

    public ItemRepositoryViewModel(@NonNull RepositoryViewModelContract.MainView view,
                                   @NonNull Repository repository) {
        this.repository = repository;
        this.view = view;
    }

    public String getName() {
        return repository.name();
    }

    public String getDescription() {
        return repository.description();
    }

    public void onItemClick(View clickedView) {
        view.onItemSelected(repository);
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
        notifyChange();
    }
}
