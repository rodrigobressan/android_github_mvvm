package com.rodrigobresan.githubmvvm.model;

import android.support.annotation.NonNull;

import com.rodrigobresan.githubmvvm.data.GithubService;
import com.rodrigobresan.githubmvvm.model.entities.Repository;

import java.util.List;

import rx.Single;

/**
 * Created by rodrigobresan on 2/25/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class RepositoryModel {

    @NonNull
    private GithubService githubService;

    public RepositoryModel(@NonNull GithubService githubService) {
        this.githubService = githubService;
    }

    @NonNull
    public Single<List<Repository>> getRepositories(String username) {
        return githubService.fetchRepositories(username);
    }
}
