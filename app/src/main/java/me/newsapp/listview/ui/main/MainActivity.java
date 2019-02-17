package me.newsapp.listview.ui.main;

import android.os.Bundle;

import me.newsapp.listview.R;
import me.newsapp.listview.base.BaseActivity;
import me.newsapp.listview.ui.list.ListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ListFragment()).commit();
    }
}
