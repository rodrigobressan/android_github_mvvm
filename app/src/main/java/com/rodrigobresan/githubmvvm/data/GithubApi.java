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

    String REPOSITORIES_PATH_USER_ID = "userId";
    String REPOSITORIES_PATH = "/users/{" + REPOSITORIES_PATH_USER_ID + "}/repos";

    /**
     * Call for returning the list of repositories of a received user
     * @param userId the id of the user that we want to fetch the repositories
     * @return the list of repositories of that user
     */
    @GET(REPOSITORIES_PATH)
    Single<List<Repository>> fetchRepositories(@Path(REPOSITORIES_PATH_USER_ID) String userId);
}
