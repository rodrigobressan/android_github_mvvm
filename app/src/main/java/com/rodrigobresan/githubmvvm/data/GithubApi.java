package com.rodrigobresan.githubmvvm.data;

import com.rodrigobresan.githubmvvm.model.entities.Repository;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Single;

/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

/**
 * This class is responsible for fetching information from GitHub API and then display to the user
 * all the repositories related to a user (on GitHub).
 */
public interface GithubApi {

    @GET("/users/{userId}/repos")
    Single<List<Repository>> fetchRepositories(@Path("userId") String userId);
}