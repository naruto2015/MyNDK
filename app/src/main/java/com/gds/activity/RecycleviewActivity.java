package com.gds.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.View;
import android.view.ViewGroup;

import com.gds.bean.DataModel;
import com.gds.recycleview.DemoAdapter;

import java.util.ArrayList;
import java.util.List;

import demo.ndk.com.myndk.R;

public class RecycleviewActivity extends Activity {


    private RecyclerView recycleview;

    private DemoAdapter adapter;

    int colors[] = {android.R.color.holo_blue_dark,android.R.color.holo_blue_light,android.R.color.holo_orange_dark};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Explode().setDuration(2000));
            getWindow().setExitTransition(new Explode().setDuration(2000));
        }


        recycleview= (RecyclerView) findViewById(R.id.recycleview);
        // recycleview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        final GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type=recycleview.getAdapter().getItemViewType(position);
                if(type==DataModel.TYPE_THREE){
                    return  gridLayoutManager.getSpanCount();
                }else{
                    return 1;
                }

            }
        });

        recycleview.setLayoutManager(gridLayoutManager);
        adapter=new DemoAdapter(this);
        recycleview.setAdapter(adapter);

        recycleview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                GridLayoutManager.LayoutParams layoutParams= (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanSize= layoutParams.getSpanSize();
                int spanIndex=layoutParams.getSpanIndex();
                outRect.top=20;
                if(spanSize!=gridLayoutManager.getSpanCount()){
                    if(spanIndex==1){
                        outRect.left=10;
                    }else{
                        outRect.right=10;
                    }
                }


            }
        });
        initData();

    }

    private void initData()
    {
        List<DataModel> list=new ArrayList<>();
        for(int i=0;i<20;i++){
            int type= (int) ((Math.random()*3)+1);
            DataModel data=new DataModel();
            data.avatarColor=colors[type-1];
            data.type=type;
            data.name="name:"+i;
            data.content="content:"+i;
            data.contentColor=colors[type-1];
            list.add(data);
        }

        adapter.addList(list);
        adapter.notifyDataSetChanged();
    }


}
