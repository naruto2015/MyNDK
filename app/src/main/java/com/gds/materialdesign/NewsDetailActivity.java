package com.gds.materialdesign;


import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.gds.materialdesign.bean.NewsBean;
import com.gds.materialdesign.bean.NewsDetailBean;
import com.gds.materialdesign.model.NewsModel;
import com.gds.materialdesign.model.OnLoadNewsDetailListener;
import com.gds.materialdesign.modelimpl.NewsModelImpl;
import com.gds.materialdesign.utils.ImageLoaderUtils;
import com.gds.materialdesign.view.NewsDetailView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import demo.ndk.com.myndk.R;

public class NewsDetailActivity extends AppCompatActivity implements NewsDetailView,OnLoadNewsDetailListener{


    private NewsBean mNews;
    private HtmlTextView mTVNewsContent;
    private ProgressBar mProgressBar;

    //private NewsDetailView mNewsDetailView;
    private NewsModel mNewsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mTVNewsContent = (HtmlTextView) findViewById(R.id.htNewsContent);
        mNewsModel = new NewsModelImpl();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        mNews = (NewsBean) getIntent().getSerializableExtra("news");

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mNews.getTitle());

        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.ivImage), mNews.getImgsrc());

        loadNewsDetail(mNews.getDocid());

    }


    private void loadNewsDetail(String docId){
        showProgress();
        mNewsModel.loadNewsDetail(docId,this);
    }


    @Override
    public void showNewsDetialContent(String newsDetailContent) {
        mTVNewsContent.setHtmlFromString(newsDetailContent, new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(NewsDetailBean newsDetailBean) {
        if (newsDetailBean != null) {
            showNewsDetialContent(newsDetailBean.getBody());
        }
        hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        hideProgress();
    }
}
