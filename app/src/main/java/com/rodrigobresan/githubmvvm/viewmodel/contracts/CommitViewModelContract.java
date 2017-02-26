package com.rodrigobresan.githubmvvm.viewmodel.contracts;

import com.rodrigobresan.githubmvvm.model.entities.Commit;

import java.util.List;

/**
 * Created by rodrigobresan on 1/19/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */
public interface CommitViewModelContract {
    interface DetailView {
        void loadCommits(List<Commit> repositoryDetails);
        void displayError();
    }

    interface ViewModel {
        void destroy();
    }
}
