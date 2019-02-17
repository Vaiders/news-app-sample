package me.newsapp.listview.ui.list;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.newsapp.listview.R;
import me.newsapp.listview.data.model.Article;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>{

    private ArticleSelectedListener articleSelectedListener;
    private final List<Article> data = new ArrayList<>();


    ArticleListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner, ArticleSelectedListener articleSelectedListener) {
        this.articleSelectedListener = articleSelectedListener;
        viewModel.getArticles().observe(lifecycleOwner, articles -> {
            data.clear();
            if (articles != null) {
                data.addAll(articles);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_news_list_item, parent, false);
        return new ArticleViewHolder(view, articleSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static final class ArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_news_title)
        TextView newsTitleTextView;
        @BindView(R.id.iv_article_image)
        ImageView articleImage;

        private Article article;

        ArticleViewHolder(View itemView, ArticleSelectedListener articleSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(article != null) {
                    articleSelectedListener.onArticleSelected(article);
                }
            });
        }
        void bind(Article article) {
            this.article = article;
            newsTitleTextView.setText(article.title);
            Picasso.get().load(article.urlToImage).resize(150,150).centerCrop().into(articleImage);

        }
    }
}
