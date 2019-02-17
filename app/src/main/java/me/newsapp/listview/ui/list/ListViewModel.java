package me.newsapp.listview.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import me.newsapp.listview.data.model.Article;
import me.newsapp.listview.data.model.ArticleList;
import me.newsapp.listview.data.rest.NewsRepository;

import static me.newsapp.listview.util.Constants.API_KEY;

public class ListViewModel extends ViewModel {

    private final NewsRepository newsRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<Article>> articles = new MutableLiveData<>();

    private final MutableLiveData<Boolean> articleLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public ListViewModel(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
        disposable = new CompositeDisposable();
        fetchArticles();
    }

    LiveData<List<Article>> getArticles() {
        return articles;
    }

    LiveData<Boolean> getError() {
        return articleLoadError;
    }
    LiveData<Boolean> getLoading() {
        return loading;
    }

    private void fetchArticles() {
        loading.setValue(true);
        disposable.add(newsRepository.getArticles(API_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<ArticleList>() {
                    @Override
                    public void onSuccess(ArticleList value) {
                        articleLoadError.setValue(false);
                        articles.setValue(value.getArticles());
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        articleLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
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
