package com.gds.materialdesign.view;

import com.gds.materialdesign.bean.NewsBean;

import java.util.List;

/**
 * Created by gaodesong on 16/11/10.
 */


public interface NewsView {

    void showProgress();

    void addNews(List<NewsBean> newsList);

    void hideProgress();

    void showLoadFailMsg();
}

