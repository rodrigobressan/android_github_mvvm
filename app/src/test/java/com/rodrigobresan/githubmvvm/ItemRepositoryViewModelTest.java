package com.rodrigobresan.githubmvvm;

import android.content.Context;

import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.viewmodel.ItemRepositoryViewModel;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Created by rodrigobresan on 2/25/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ItemRepositoryViewModelTest {

    private static final String REPOSITORY_NAME = "Repository Name";
    private static final String REPOSITORY_DESCRIPTION = "Repository Description";

    @Mock
    Context mockedContext;

    @Mock
    RepositoryViewModelContract.MainView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnRepositoryName() {
        Repository repository = Repository.builder().name(REPOSITORY_NAME).build();

        ItemRepositoryViewModel itemRepositoryViewModel = new ItemRepositoryViewModel(view, repository);

        assertEquals(repository.name(), itemRepositoryViewModel.getName());
    }

    @Test
    public void shouldReturnRepositoryDescription() {
        Repository repository = Repository.builder().description(REPOSITORY_DESCRIPTION).build();

        ItemRepositoryViewModel itemRepositoryViewModel = new ItemRepositoryViewModel(view, repository);

        assertEquals(repository.description(), itemRepositoryViewModel.getDescription());
    }
}
