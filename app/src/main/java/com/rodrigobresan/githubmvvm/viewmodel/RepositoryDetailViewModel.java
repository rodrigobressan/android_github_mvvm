package com.rodrigobresan.githubmvvm.viewmodel;

import android.databinding.BaseObservable;

import com.rodrigobresan.githubmvvm.model.Repository;

/**
 * Created by rodrigobresan on 1/19/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class RepositoryDetailViewModel extends BaseObservable {

    private Repository repository;

    public RepositoryDetailViewModel(Repository repository) {
        this.repository = repository;
    }

    public String getName() {
        return repository.name;
    }

    public String getDescription() {
        return repository.description;
    }
}
