package me.newsapp.listview.data.rest;

import io.reactivex.Single;
import me.newsapp.listview.data.model.ArticleList;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("top-headlines?country=us")
    Single<ArticleList> getArticles(@Query("apiKey") String apiKey);
}
