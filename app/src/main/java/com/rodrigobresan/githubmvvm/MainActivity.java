package com.rodrigobresan.githubmvvm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rodrigobresan.githubmvvm.rx.MyRxBus;
import com.rodrigobresan.githubmvvm.view.RepositoriesFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_main_content,
                        new RepositoriesFragment(),
                        "repositories").commit();

        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab_load_repositories)
    public void onLoadRepositoriesClick() {
        MyRxBus.getInstance().setString("fab_click");
    }
}
