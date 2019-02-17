package me.newsapp.listview.data.rest;

import javax.inject.Inject;

import io.reactivex.Single;
import me.newsapp.listview.data.model.ArticleList;

public class NewsRepository {

    private final APIService apiService;

    @Inject
    public NewsRepository(APIService apiService) {
        this.apiService = apiService;
    }

    public Single<ArticleList> getArticles(String apiKey) {
        return apiService.getArticles(apiKey);
    }

}
