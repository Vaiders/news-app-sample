package me.newsapp.listview.data.model;

public class Article {

    public final long id;
    public final String author;
    public final String title;
    public final String description;
    public final String url;
    public final String urlToImage;

    public Article(long id, String author, String title, String description, String url, String urlToImage) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
    }
}
