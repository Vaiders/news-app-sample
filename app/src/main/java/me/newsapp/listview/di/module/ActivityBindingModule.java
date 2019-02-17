package me.newsapp.listview.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.newsapp.listview.ui.main.MainActivity;
import me.newsapp.listview.ui.main.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
