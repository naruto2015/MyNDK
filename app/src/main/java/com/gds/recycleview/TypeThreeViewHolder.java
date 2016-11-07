package com.gds.recycleview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gds.bean.DataModel;

import demo.ndk.com.myndk.R;

/**
 * Created by naruto on 2016/10/27.
 */
public class TypeThreeViewHolder extends TypeAbstractViewHolder{

    public ImageView avatar;
    public TextView name;
    public TextView content;
    public ImageView contentImage;

    public TypeThreeViewHolder(View itemView) {
        super(itemView);
        avatar= (ImageView) itemView.findViewById(R.id.avatar);
        name= (TextView) itemView.findViewById(R.id.name);
        content= (TextView) itemView.findViewById(R.id.content);
        contentImage= (ImageView) itemView.findViewById(R.id.contentImage);
    }

    @Override
    public void bindHolder(DataModel model) {
        avatar.setBackgroundResource(model.avatarColor);
        name.setText(model.name);
        contentImage.setBackgroundResource(model.contentColor);
        content.setText(model.content);
    }


}
