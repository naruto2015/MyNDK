package com.gds.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gds.bean.DataModel;

import java.util.ArrayList;
import java.util.List;

import demo.ndk.com.myndk.R;

/**
 * Created by naruto on 2016/10/27.
 */
public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater inflater;
    private List<DataModel> modelList=new ArrayList<>();
    public DemoAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }


    public void addList(List<DataModel> list){
        modelList.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case DataModel.TYPE_ONE:
                return new TypeOneViewHolder(inflater.inflate(R.layout.item_type_one,parent,false));
            case DataModel.TYPE_TWO:
                return new TypeOneViewHolder(inflater.inflate(R.layout.item_type_two,parent,false));
            case DataModel.TYPE_THREE:
                return new TypeOneViewHolder(inflater.inflate(R.layout.item_type_three,parent,false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //int viewType=getItemViewType(position);
        ((TypeAbstractViewHolder) holder).bindHolder(modelList.get(position));

    }

    @Override
    public int getItemViewType(int position) {
        return modelList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


}
