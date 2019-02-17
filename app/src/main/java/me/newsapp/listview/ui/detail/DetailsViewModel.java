package me.newsapp.listview.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import me.newsapp.listview.data.rest.NewsRepository;

public class DetailsViewModel extends ViewModel {

    private final NewsRepository newsRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<String> selectedArticle = new MutableLiveData<>();

    public LiveData<String> getSelectedArticle() {
        return selectedArticle;
    }

    @Inject
    public DetailsViewModel(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
        disposable = new CompositeDisposable();
    }

    public void setSelectedArticle(String url) {
        selectedArticle.setValue(url);
    }

    public void saveToBundle(Bundle outState) {
        if(selectedArticle.getValue() != null) {
            outState.putString("article_details", selectedArticle.getValue());
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if(selectedArticle.getValue() == null) {
            if(savedInstanceState != null && savedInstanceState.containsKey("article_details")) {
                selectedArticle.setValue(savedInstanceState.getString("article_details"));
            }
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
