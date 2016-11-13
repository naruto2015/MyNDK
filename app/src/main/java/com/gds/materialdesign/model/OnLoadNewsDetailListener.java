package com.gds.materialdesign.model;

import com.gds.materialdesign.bean.NewsDetailBean;

/**
 * Created by gaodesong on 16/11/10.
 * 新闻详情加载回调
 */

public interface OnLoadNewsDetailListener {

    void onSuccess(NewsDetailBean newsDetailBean);

    void onFailure(String msg, Exception e);
}