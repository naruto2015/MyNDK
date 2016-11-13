package com.gds.materialdesign.model;

import com.gds.materialdesign.bean.NewsBean;

import java.util.List;

/**
 * Created by gaodesong on 16/11/10.
 */

public interface OnLoadNewsListListener {

    void onSuccess(List<NewsBean> list);

    void onFailure(String msg, Exception e);
}
