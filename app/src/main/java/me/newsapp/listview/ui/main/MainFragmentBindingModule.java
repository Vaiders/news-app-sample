package me.newsapp.listview.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.newsapp.listview.ui.detail.DetailsFragment;
import me.newsapp.listview.ui.list.ListFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment provideDetailsFragment();
}
