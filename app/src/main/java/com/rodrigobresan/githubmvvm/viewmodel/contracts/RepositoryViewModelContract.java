package com.rodrigobresan.githubmvvm.viewmodel.contracts;

import android.content.Context;

import com.rodrigobresan.githubmvvm.model.Repository;

import java.util.List;

/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public interface RepositoryViewModelContract {

    interface MainView {
        Context getContext();
        void displayError();
        void loadData(List<Repository> repositoryList);
    }

    interface ViewModel {
        void destroy();
    }
}
