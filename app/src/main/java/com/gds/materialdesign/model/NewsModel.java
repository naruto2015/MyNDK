package com.gds.materialdesign.model;

/**
 * Created by gaodesong on 16/11/10.
 */

public interface NewsModel {

    void loadNews(String url, int type, OnLoadNewsListListener listener);

    void loadNewsDetail(String docid, OnLoadNewsDetailListener listener);

}
