package com.rodrigobresan.githubmvvm.model;

import android.support.annotation.NonNull;

import com.rodrigobresan.githubmvvm.data.GithubApi;
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
    private GithubApi githubApi;

    public RepositoryModel(@NonNull GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    @NonNull
    public Single<List<Repository>> getRepositories(String username) {
        return githubApi.fetchRepositories(username);
    }
}
