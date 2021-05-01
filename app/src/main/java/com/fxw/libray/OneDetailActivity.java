package com.fxw.libray;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianwen.fu
 * @version v1.0
 * @Title
 * @Description:
 * @Date 2016/04/08下午 4:42
 */
public class OneDetailActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private ListView mListview;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_one);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("叶良辰");
        mListview = (ListView)findViewById(R.id.detail_one_list);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        MyAdapter mAdapter = new MyAdapter();
        mListview.setAdapter(mAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            ViewHolder mHolder = null;
            if (v == null ){
                v = getLayoutInflater().inflate(R.layout.listview_detail_one,parent,false);
                mHolder = new ViewHolder();
                mHolder.textView = (TextView)v.findViewById(R.id.listview_detail_one_tv);
                v.setTag(mHolder);
            }else mHolder = (ViewHolder) v.getTag();
            return v;
        }
        private class ViewHolder{
            TextView textView;
        }
    }

}
