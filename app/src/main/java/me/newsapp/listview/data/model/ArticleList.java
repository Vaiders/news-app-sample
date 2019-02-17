package me.newsapp.listview.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleList {
    @SerializedName("articles")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }
}
