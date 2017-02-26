package com.rodrigobresan.githubmvvm.viewmodel;

import android.databinding.BaseObservable;

import com.rodrigobresan.githubmvvm.model.entities.Commit;

/**
 * Created by rodrigobresan on 1/27/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class ItemCommitViewModel extends BaseObservable {

    private Commit commit;

    public ItemCommitViewModel(Commit commit) {
        this.commit = commit;
    }

    public String getSha() {
        return commit.sha;
    }

    public String getMessage() {
        return commit.commit.message;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
        notifyChange();
    }
}
