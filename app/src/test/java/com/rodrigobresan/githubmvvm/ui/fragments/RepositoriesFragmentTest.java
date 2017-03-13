package com.rodrigobresan.githubmvvm.ui.fragments;

import com.rodrigobresan.githubmvvm.GithubRobolectricUnitTestRunner;
import com.rodrigobresan.githubmvvm.view.RepositoriesFragment;
import com.rodrigobresan.githubmvvm.viewmodel.RepositoryViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by rodrigobresan on 3/1/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

@RunWith(GithubRobolectricUnitTestRunner.class)
public class RepositoriesFragmentTest {

    @Test
    public void onFabPressed_shouldLoadDataFromServer() {
        RepositoriesFragment repositoriesFragment = new RepositoriesFragment();
        RepositoryViewModel repositoryViewModel = mock(RepositoryViewModel.class);

        repositoriesFragment.repositoryViewModel = repositoryViewModel;

        repositoriesFragment.onClickFab();

        verify(repositoryViewModel).loadData();
    }

}
