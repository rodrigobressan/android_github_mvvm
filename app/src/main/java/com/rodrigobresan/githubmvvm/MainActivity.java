package com.rodrigobresan.githubmvvm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rodrigobresan.githubmvvm.rx.MyRxBus;
import com.rodrigobresan.githubmvvm.util.FragmentHelper;
import com.rodrigobresan.githubmvvm.view.RepositoriesFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private FragmentHelper fragmentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragmentHelper();
        addRepositoriesFragmentIntoScreen();
        ButterKnife.bind(this);
    }

    private void initFragmentHelper() {
        fragmentHelper = new FragmentHelper(getSupportFragmentManager(), R.id.layout_main_content);
    }

    private void addRepositoriesFragmentIntoScreen() {
        fragmentHelper.addFragment(new RepositoriesFragment(), RepositoriesFragment.TAG);
    }

    @OnClick(R.id.fab_load_repositories)
    public void onLoadRepositoriesClick() {
        MyRxBus.getInstance().setString("fab_click");
    }
}
