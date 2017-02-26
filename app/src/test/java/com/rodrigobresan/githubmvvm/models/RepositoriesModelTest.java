package com.rodrigobresan.githubmvvm.models;

import com.rodrigobresan.githubmvvm.data.GithubService;
import com.rodrigobresan.githubmvvm.model.Repository;
import com.rodrigobresan.githubmvvm.model.RepositoryModel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.Single;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by rodrigobresan on 2/25/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class RepositoriesModelTest {

    private GithubService githubService;
    private RepositoryModel repositoryModel;

    @Before
    public void beforeEachTest() {
        githubService = mock(GithubService.class);
        repositoryModel = new RepositoryModel(githubService);
    }

    @Test
    public void getRepositories_shouldReturnRepositoriesFromApi() {
        List<Repository> repositoryList = asList(mock(Repository.class), mock(Repository.class));
        when(githubService.fetchRepositories("bresan")).thenReturn(Single.just(repositoryList));

        assertThat(repositoryModel.getRepositories("bresan").toBlocking().value())
                .containsExactlyElementsOf(repositoryList);
    }
}
