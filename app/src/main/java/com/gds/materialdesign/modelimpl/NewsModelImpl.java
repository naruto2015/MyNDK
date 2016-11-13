package com.gds.materialdesign.modelimpl;

import com.gds.materialdesign.bean.NewsBean;
import com.gds.materialdesign.bean.NewsDetailBean;
import com.gds.materialdesign.fragment.NewsFragment;
import com.gds.materialdesign.model.NewsModel;
import com.gds.materialdesign.model.OnLoadNewsDetailListener;
import com.gds.materialdesign.model.OnLoadNewsListListener;
import com.gds.materialdesign.utils.NewsJsonUtils;
import com.gds.materialdesign.utils.OkHttpUtils;
import com.gds.materialdesign.utils.Utils;


import java.util.List;

/**

 */
public class NewsModelImpl implements NewsModel {

    /**
     * 加载新闻列表
     * @param url
     * @param listener
     */
    @Override
    public void loadNews(String url, final int type, final OnLoadNewsListListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<NewsBean> newsBeanList = NewsJsonUtils.readJsonNewsBeans(response, getID(type));
                listener.onSuccess(newsBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news list failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }

    /**
     * 加载新闻详情
     * @param docid
     * @param listener
     */
    @Override
    public void loadNewsDetail(final String docid, final OnLoadNewsDetailListener listener) {
        String url = getDetailUrl(docid);
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docid);
                listener.onSuccess(newsDetailBean);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news detail info failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }

    /**
     * 获取ID
     * @param type
     * @return
     */
    private String getID(int type) {
        String id;
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                id = Utils.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Utils.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = Utils.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = Utils.JOKE_ID;
                break;
            default:
                id = Utils.TOP_ID;
                break;
        }
        return id;
    }

    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer(Utils.NEW_DETAIL);
        sb.append(docId).append(Utils.END_DETAIL_URL);
        return sb.toString();
    }

}
