package com.gds.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gds.bean.DataModel;

import demo.ndk.com.myndk.R;

/**
 * Created by naruto on 2016/10/27.
 */
public class TypeTwoViewHolder extends TypeAbstractViewHolder{

    public ImageView avatar;
    public TextView name;

    public TextView content;

    public TypeTwoViewHolder(View itemView) {
        super(itemView);
        avatar= (ImageView) itemView.findViewById(R.id.avatar);
        name= (TextView) itemView.findViewById(R.id.name);
        content= (TextView) itemView.findViewById(R.id.content);
    }

    @Override
    public void bindHolder(DataModel model) {
        avatar.setBackgroundResource(model.avatarColor);
        name.setText(model.name);
        content.setText(model.content);
    }


}
