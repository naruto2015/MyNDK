package com.gds.materialdesign.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gds.materialdesign.NewsDetailActivity;
import com.gds.materialdesign.adapter.NewsAdapter;
import com.gds.materialdesign.bean.NewsBean;
import com.gds.materialdesign.model.NewsModel;
import com.gds.materialdesign.model.OnLoadNewsListListener;
import com.gds.materialdesign.modelimpl.NewsModelImpl;
import com.gds.materialdesign.utils.Utils;
import com.gds.materialdesign.view.NewsView;

import java.util.ArrayList;
import java.util.List;

import demo.ndk.com.myndk.R;

/**
 * Created by gaodesong on 16/11/10.
 */

public class NewsListFragment extends Fragment implements NewsView,OnLoadNewsListListener,SwipeRefreshLayout.OnRefreshListener{


    private static final String TAG = "NewsListFragment";

    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mAdapter;
    private List<NewsBean> mData;


    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private int pageIndex = 0;

    private NewsModel newsModel=new NewsModelImpl();

    public static NewsListFragment newInstance(int type) {
        Bundle bundle=new Bundle();
        NewsListFragment fragment=new NewsListFragment();
        bundle.putInt("type",type);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mType=getArguments().getInt("type");

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_newslist, null);
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);   //必须设置

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter(getActivity().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();


        return view;
    }


    private RecyclerView.OnScrollListener mOnScrollListener=new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            /*
            SCROLL_STATE_FLING，这个参数表示你手离开后ListView还在“飞”中，理解？
            SCROLL_STATE_IDLE，这个参数表示ListView停下不动了
            SCROLL_STATE_TOUCH_SCROLL，这个参数表示你手还在ListView上
             */
            if(newState==RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem+1==mAdapter.getItemCount()
                    && mAdapter.isShowFooter()){

                //加载更多
                loadNews(mType,pageIndex+Utils.PAZE_SIZE);
            }

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem=mLayoutManager.findLastVisibleItemPosition();
        }
    };



    private void loadNews(int type, int page){

        String url= Utils.getUrl(type,pageIndex);
        //只有第一页的或者刷新的时候才显示刷新进度条
        if(pageIndex == 0) {
            showProgress();
        }
        newsModel.loadNews(url,type,this);

    }

    private NewsAdapter.OnItemClickListener mOnItemClickListener=new NewsAdapter.OnItemClickListener(){

        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) {
                return;
            }
            NewsBean news = mAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("news", news);

            View transitionView = view.findViewById(R.id.ivNews);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            transitionView, getString(R.string.transition_news_img));

            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        }
    };


    @Override
    public void onRefresh() {
        pageIndex = 0;
        if(mData != null) {
            mData.clear();
        }
        loadNews(mType,pageIndex);
    }


    @Override
    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void addNews(List<NewsBean> newsList) {

        mAdapter.isShowFooter(true);
        if(mData==null){
            mData=new ArrayList<>();
        }
        mData.addAll(newsList);
        if(pageIndex==0){
            mAdapter.setmDate(mData);
        }else {
            //如果没有更多数据了,则隐藏footer布局
            if(newsList==null || newsList.size()==0){
                mAdapter.isShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex += Utils.PAZE_SIZE;


    }

    @Override
    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        if(pageIndex == 0) {
            mAdapter.isShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id.drawer_layout);
        Snackbar.make(view, getString(R.string.load_fail), Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void onSuccess(List<NewsBean> list) {
        this.hideProgress();
        this.addNews(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        this.hideProgress();
        this.showLoadFailMsg();
    }
}
