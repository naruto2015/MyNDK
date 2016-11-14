package com.gds.materialdesign.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.gds.materialdesign.adapter.MediaAdapter;
import com.gds.materialdesign.bean.MediaBean;
import com.gds.materialdesign.bean.NewsDetailBean;
import com.gds.materialdesign.bean.VideoBean;
import com.gds.materialdesign.utils.NewsJsonUtils;
import com.gds.materialdesign.utils.OkHttpUtils;
import com.gds.materialdesign.view.ImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import demo.ndk.com.myndk.R;

public class VideoListActivity extends AppCompatActivity {


    private List<VideoBean> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private MediaAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);


        initView();

        initdata();




    }

    private String str;
    private void initdata() {
        String url="https://newapi.meipai.com//hot/feed_timeline.json";
        List<OkHttpUtils.Param> params=new ArrayList<>();
        OkHttpUtils.ResultCallback<String> loadVideoCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {

                str=response;
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(Exception e) {

            }
        };


        OkHttpUtils.post(url,loadVideoCallback,params);

    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Gson gson=new Gson();
            if(list!=null){
                list.clear();
            }
            list=gson.fromJson(str,new TypeToken<List<VideoBean>>(){}.getType());
            list.remove(0);


            adapter.setDate(list);
        }
    };

    private void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.recycle_view);

        adapter=new MediaAdapter(VideoListActivity.this);
        recyclerView.setHasFixedSize(true);   //必须设置
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //mLayoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new );
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


}
