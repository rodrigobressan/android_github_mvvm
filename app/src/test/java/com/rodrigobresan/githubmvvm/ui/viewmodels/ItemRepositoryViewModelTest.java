package com.rodrigobresan.githubmvvm.ui.viewmodels;

import com.rodrigobresan.githubmvvm.GithubRobolectricUnitTestRunner;
import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.viewmodel.ItemRepositoryViewModel;
import com.rodrigobresan.githubmvvm.viewmodel.contracts.RepositoryViewModelContract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by rodrigobresan on 3/8/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

@RunWith(GithubRobolectricUnitTestRunner.class)
public class ItemRepositoryViewModelTest {

    private static final String REPOSITORY_NAME_TEST = "Test Name Repository";
    private static final String REPOSITORY_DESCRIPTION_TEST = "Test Description Repository";

    @Mock
    RepositoryViewModelContract.MainView mainView;

    @Test
    public void shouldGetRepositoryName() {
        Repository repository = Repository.builder().name(REPOSITORY_NAME_TEST).build();

        ItemRepositoryViewModel itemRepositoryViewModel = new ItemRepositoryViewModel(mainView, repository);

        assertThat(repository.name()).isEqualTo(itemRepositoryViewModel.getName());
    }

    @Test
    public void shouldGetRepositoryDescription() {
        Repository repository = Repository.builder().description(REPOSITORY_DESCRIPTION_TEST).build();

        ItemRepositoryViewModel itemRepositoryViewModel = new ItemRepositoryViewModel(mainView, repository);

        assertThat(repository.description()).isEqualTo(itemRepositoryViewModel.getDescription());
    }
}
