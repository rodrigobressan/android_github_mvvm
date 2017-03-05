package com.rodrigobresan.githubmvvm.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.rodrigobresan.githubmvvm.GithubRobolectricUnitTestRunner;
import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.util.CustomImageLoader;
import com.rodrigobresan.githubmvvm.view.RepositoryAdapter;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by rodrigobresan on 2/26/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

@RunWith(GithubRobolectricUnitTestRunner.class)
public class RepositoriesAdapterTest {

    private LayoutInflater layoutInflater;
    private RepositoryAdapter repositoryAdapter;

    @Mock
    CustomImageLoader imageLoader;

    @Mock
    RepositoryViewModelContract.MainView view;

    @Before
    public void beforeEachTest() {
        layoutInflater = mock(LayoutInflater.class);
        repositoryAdapter = new RepositoryAdapter(view, layoutInflater);
    }

    @Test
    public void getRepositoryCount_shouldBeZeroByDefault() {
        assertThat(repositoryAdapter.getItemCount()).isEqualTo(0);
    }

    @Test
    public void getRepositoryCount_shouldReturnCorrectValueAfterSetData() {
        repositoryAdapter.setRepositoryList(asList(mock(Repository.class), mock(Repository.class)));
        assertThat(repositoryAdapter.getItemCount()).isEqualTo(2);
    }

    @Test
    public void setData_shouldNotifyObserversAboutChange() {
        RecyclerView.AdapterDataObserver observer = mock(RecyclerView.AdapterDataObserver.class);
        repositoryAdapter.registerAdapterDataObserver(observer);

        repositoryAdapter.setRepositoryList(Collections.<Repository>emptyList());
        verify(observer).onChanged();
        verifyNoMoreInteractions(observer);
    }

    @Test
    public void onBindViewHolder_shouldBindRepositoriesToViewHolders() {
        List<Repository> repositoryList = asList(
                Repository.builder().id(1).name("Repo 1").description("Repo 1 desc").build(),
                Repository.builder().id(1).name("Repo 2").description("Repo 2 desc").build(),
                Repository.builder().id(1).name("Repo 3").description("Repo 3 desc").build()
        );

        repositoryAdapter.setRepositoryList(repositoryList);

        for (int position = 0; position < repositoryList.size(); position++) {
            RepositoryAdapter.RepositoryViewHolder repositoryViewHolder = mock(RepositoryAdapter.RepositoryViewHolder.class);
            repositoryAdapter.onBindViewHolder(repositoryViewHolder, position);
            verify(repositoryViewHolder).bindRepository(repositoryList.get(position));
        }
    }

}
