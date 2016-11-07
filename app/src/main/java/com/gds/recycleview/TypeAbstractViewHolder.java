package com.gds.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gds.bean.DataModel;

/**
 * Created by naruto on 2016/10/27.
 */
public abstract class TypeAbstractViewHolder extends RecyclerView.ViewHolder {
    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(DataModel model);

}
