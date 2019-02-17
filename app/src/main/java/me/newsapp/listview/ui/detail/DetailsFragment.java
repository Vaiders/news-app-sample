package me.newsapp.listview.ui.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import javax.inject.Inject;

import butterknife.BindView;
import me.newsapp.listview.R;
import me.newsapp.listview.base.BaseFragment;
import me.newsapp.listview.util.ViewModelFactory;

public class DetailsFragment extends BaseFragment {

    @BindView(R.id.web_view)
    WebView webView;

    @Inject
    ViewModelFactory viewModelFactory;
    private DetailsViewModel detailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.screen_web_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailsViewModel.class);
        detailsViewModel.restoreFromBundle(savedInstanceState);
        displayArticle();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        detailsViewModel.saveToBundle(outState);
    }

    private void displayArticle() {
        detailsViewModel.getSelectedArticle().observe(this, article -> {
            if (article != null) {
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(article);
            }
        });
    }
}
