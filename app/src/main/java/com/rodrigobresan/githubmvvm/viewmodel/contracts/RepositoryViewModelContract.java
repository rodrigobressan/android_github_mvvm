package com.rodrigobresan.githubmvvm.viewmodel.contracts;

import com.rodrigobresan.githubmvvm.model.entities.Repository;

import java.util.List;

/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public interface RepositoryViewModelContract {

    interface MainView {
        void displayError();
        void loadData(List<Repository> repositoryList);
        void onItemSelected(Repository repository);
    }

    interface ViewModel {
        void destroy();
        void loadData();
    }
}
