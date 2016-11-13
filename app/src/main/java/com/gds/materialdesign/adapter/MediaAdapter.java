package com.gds.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.gds.materialdesign.bean.VideoBean;
import com.gds.materialdesign.utils.ImageLoaderUtils;


import java.util.ArrayList;
import java.util.List;

import demo.ndk.com.myndk.R;

/**
 * Created by gaodesong on 16/11/13.
 */

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MyViewHolder>{



    List<VideoBean> list=new ArrayList<>();
    Context context;

    private LayoutInflater mInflater;

    //private List<Integer> mHeights;
    private int height;
    private int width;

    public MediaAdapter(Context context,List<VideoBean> list){
        this.list=list;
        this.context=context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        MyViewHolder holder=new MyViewHolder(mInflater.inflate(R.layout.view_video_list_item,null));



        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String[] se=list.get(position).media.pic_size.split("*");
        width= Integer.parseInt(se[1]);
        height= Integer.parseInt(se[0]);
        ViewGroup.LayoutParams lp = holder.video_img.getLayoutParams();
        lp.height=height;
        lp.width=width;
        holder.video_img.setLayoutParams(lp);

        ImageLoaderUtils.display(context,holder.video_img,list.get(position).media.cover_pic);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        public ImageView video_img;
        public MyViewHolder(View itemView) {
            super(itemView);
            video_img= (ImageView) itemView.findViewById(R.id.video_img);
        }




    }





}
