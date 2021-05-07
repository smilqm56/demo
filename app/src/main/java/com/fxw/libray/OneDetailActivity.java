package com.fxw.libray;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fxw.mylibrary.adapter.BaseQuickAdapter;
import com.fxw.mylibrary.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianwen.fu
 * @version v1.0
 * @Title
 * @Description:
 * @Date 2016/04/08下午 4:42
 */
public class OneDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView mListview;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_one);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("叶良辰");
        mListview = (RecyclerView) findViewById(R.id.detail_one_list);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        mListview.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter mAdapter = new MyAdapter(this);
        mListview.setAdapter(mAdapter);
        mAdapter.addAll(list);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private class MyAdapter extends BaseQuickAdapter<String> {

        public MyAdapter(Context context) {
            super(context, R.layout.listview_detail_one);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.listview_detail_one_tv, "看工行卡号部分看见俺不会分开不哈斯分别汇报汇报hi奥斯卡按时开放办卡时发布可不能老是不可不卡不卡了办三个三个石头哥特");
        }

    }

}
