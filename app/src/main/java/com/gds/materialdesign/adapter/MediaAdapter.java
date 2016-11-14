package com.gds.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.gds.materialdesign.bean.MediaBean;
import com.gds.materialdesign.bean.NewsBean;
import com.gds.materialdesign.bean.VideoBean;
import com.gds.materialdesign.utils.ImageLoaderUtils;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import demo.ndk.com.myndk.R;

/**
 * Created by gaodesong on 16/11/13.
 */

public class MediaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{



    List<VideoBean> list=null;
    Context context;

    private LayoutInflater mInflater;

    private List<Integer> mHeights=new ArrayList<>();


    public void setDate(List<VideoBean> data) {
        list=data;
        mHeights.clear();
        for (int i = 0; i < list.size(); i++)
        {
            mHeights.add( (int) (290 + Math.random() * 400));
            if(list.get(i).media==null){
                list.remove(i);
            }
        }

        notifyDataSetChanged();
    }

        public MediaAdapter(Context context,List<VideoBean> list){
            this.list=list;
            this.context=context;
            mInflater = LayoutInflater.from(context);

        }

        public MediaAdapter(Context context){

            this.context=context;
            mInflater = LayoutInflater.from(context);


        }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        MyViewHolder holder=new MyViewHolder(mInflater.inflate(R.layout.view_video_list_item,parent,false));


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if(list!=null){
            ViewGroup.LayoutParams lp = ((MyViewHolder) holder).video_img.getLayoutParams();
            lp.height=mHeights.get(position);
            ((MyViewHolder) holder).video_img.setLayoutParams(lp);
            MediaBean mediaBean=new MediaBean();
            mediaBean=list.get(position).media;
            ImageLoaderUtils.display(context,((MyViewHolder) holder).video_img,mediaBean.cover_pic);
            ImageLoaderUtils.display(context,((MyViewHolder) holder).avatar,list.get(position).user.avatar);
        }



    }

    @Override
    public int getItemCount() {
        if(list!=null && list.size()>0){
            return list.size();
        }
        return 0;

    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        public ImageView video_img;
        public CircleImageView avatar;
        public MyViewHolder(View itemView) {
            super(itemView);
            video_img= (ImageView) itemView.findViewById(R.id.video_img);
            avatar= (CircleImageView) itemView.findViewById(R.id.avatar);
        }




    }





}
