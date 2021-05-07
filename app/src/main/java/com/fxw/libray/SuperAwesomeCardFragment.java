/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fxw.libray;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fxw.mylibrary.adapter.BaseQuickAdapter;
import com.fxw.mylibrary.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SuperAwesomeCardFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;
    private static final int[] drawables = {R.drawable.f, R.drawable.s, R.drawable.t, R.drawable.fo,
            R.drawable.fi, R.drawable.fi, R.drawable.fi, R.drawable.fi};
    private RecyclerView mListview;
    private List<String> list;
    private LinearLayout mGroup;

    public static SuperAwesomeCardFragment newInstance(int position) {
        SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGroup = (LinearLayout) view.findViewById(R.id.frag_one_group);
        mListview = (RecyclerView) view.findViewById(R.id.frag_one_list);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        mGroup.setBackgroundResource(drawables[position]);
        mListview.getBackground().setAlpha((int) (255 * 0.7));
        mListview.setLayoutManager(new LinearLayoutManager(getContext()));
        MyAdapter mAdapter = new MyAdapter(getContext());
        mListview.setAdapter(mAdapter);
        mAdapter.addAll(list);
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(), mGroup, "叶良辰");
                startActivity(new Intent(getActivity(), OneDetailActivity.class),options.toBundle());
            }
        });
    }


    /**
     * 提供当前Fragment的主色调的Bitmap对象,供Palette解析颜色
     *
     * @return
     */
    public static int getBackgroundBitmapPosition(int selectViewPagerItem) {
        return drawables[selectViewPagerItem];
    }


    private class MyAdapter extends BaseQuickAdapter<String> {


        public MyAdapter(Context context) {
            super(context, R.layout.listview_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }

    }


}