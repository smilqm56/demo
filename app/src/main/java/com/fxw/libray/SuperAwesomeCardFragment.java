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

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.fxw.mylibrary.adapter.BaseQuickAdapter;
import com.fxw.mylibrary.adapter.BaseViewHolder;
import com.fxw.mylibrary.util.LogUtils;
import com.fxw.mylibrary.util.PermissionsUtil;
import com.fxw.mylibrary.util.ToastUtils;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SuperAwesomeCardFragment extends Fragment implements PermissionsUtil.RequestPerissionsListeners{

    private static final String ARG_POSITION = "position";
    private static final int REQUECT_CODE_SDCARD = 10;
    private View mView;
    private int mPosition;
    private static final int[] drawables = {R.drawable.f, R.drawable.s, R.drawable.t, R.drawable.fo,
            R.drawable.fi, R.drawable.fi, R.drawable.fi, R.drawable.fi};
    private PullLoadMoreRecyclerView mListview;
    private List<String> list;
    private LinearLayout mGroup;
    private MyAdapter mAdapter;

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

        mPosition = getArguments().getInt(ARG_POSITION);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGroup = (LinearLayout) view.findViewById(R.id.frag_one_group);
        mListview = (PullLoadMoreRecyclerView) view.findViewById(R.id.frag_one_list);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("");
        }
        mGroup.setBackgroundResource(drawables[mPosition]);
        mListview.getBackground().setAlpha((int) (255 * 0.7));
        mListview.setLinearLayout();
        mAdapter = new MyAdapter(getContext());
        mListview.setAdapter(mAdapter);
        mListview.setOnPullLoadMoreListener(new PullLoadMoreListener());
        getData();
        PermissionsUtil.setRequestPerissionsListeners(this);
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {


            @Override
            public void onItemClick(View view, int position) {
                mView = view;
                boolean mPermission = PermissionsUtil.requestArrayPermissions(SuperAwesomeCardFragment.this, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10);
                if (mPermission) {
                    startActivity(view);
                }
            }
        });
    }

    private void startActivity(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(view, (int) view.getX(), (int) view.getY(), view.getWidth(), view.getHeight());
//                tivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity());
//                tmapDrawable bd = (BitmapDrawable) getActivity().getResources().getDrawable(drawables[mPosition]);
//                tmap bitmap = bd.getBitmap();
//                tivityOptionsCompat options = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(view, bitmap, (int) view.getX(), (int) view.getY());
        startActivity(new Intent(getActivity(), OneDetailActivity.class), options.toBundle());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtil.onRequestPermissionsResult(getActivity(), requestCode, permissions, grantResults);
    }

    /**
     * 提供当前Fragment的主色调的Bitmap对象,供Palette解析颜色
     *
     * @return
     */
    public static int getBackgroundBitmapPosition(int selectViewPagerItem) {
        return drawables[selectViewPagerItem];
    }

    @Override
    public void requestPerissionsSuccess(int requestCode) {
        startActivity(mView);
    }

    @Override
    public void requestPerissionsFailed(int requestCode, List<String> deniedPermissions) {
        ToastUtils.showToast(getActivity(), requestCode + "失败" + deniedPermissions.get(0));
        LogUtils.LOGE("权限获取", requestCode + "失败" + deniedPermissions.get(0));
    }


    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            setRefresh();
            getData();
            mListview.setHasMore(true);
        }

        @Override
        public void onLoadMore() {
            getData();
            mListview.setHasMore(false);
        }
    }

    private void setRefresh() {
        mAdapter.clear();
    }


    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addAll(list);
                mListview.setPullLoadMoreCompleted();
            }
        }, 3000);
    }

    private class MyAdapter extends BaseQuickAdapter<String> {


        public MyAdapter(Context context) {
            super(context, R.layout.listview_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setImageUrl(R.id.listview_iv, ImageList.getImagerUrl());
        }

    }


}