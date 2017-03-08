package com.rodrigobresan.githubmvvm.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.rodrigobresan.githubmvvm.R;
import com.rodrigobresan.githubmvvm.view.RepositoriesFragment;

/**
 * Created by rodrigobresan on 3/8/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class FragmentHelper {

    private FragmentManager fragmentManager;
    private int layoutResourceId;

    public FragmentHelper(FragmentManager fragmentManager, int layoutResourceId) {
        this.fragmentManager = fragmentManager;
        this.layoutResourceId = layoutResourceId;
    }

    public void addFragment(Fragment fragment, String tag) {
        fragmentManager.beginTransaction().add(layoutResourceId, fragment, tag).commit();
    }
}
