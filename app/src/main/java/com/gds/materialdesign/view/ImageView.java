package com.gds.materialdesign.view;

import com.gds.materialdesign.bean.ImageBean;

import java.util.List;

/**
 * Created by gaodesong on 16/11/11.
 */

public interface ImageView {
    void addImages(List<ImageBean> list);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
