package com.rodrigobresan.githubmvvm.ui.fragments;

import android.view.View;

import com.rodrigobresan.githubmvvm.GithubRobolectricUnitTestRunner;
import com.rodrigobresan.githubmvvm.MainActivity;
import com.rodrigobresan.githubmvvm.data.GithubApi;
import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.view.RepositoriesFragment;
import com.rodrigobresan.githubmvvm.viewmodel.RepositoryViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Scheduler;

import static java.util.Arrays.asList;
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

        repositoriesFragment.onFabClick();

        verify(repositoryViewModel).loadData();
    }

}
