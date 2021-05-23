package com.fxw.mylibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianwen.fu
 * @version v1.0
 * @Title
 * @Description:
 * @Date 2016/04/11下午 3:16
 */
public abstract class BaseQuickAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();

    protected final Context context;

    protected final int layoutResId;

    protected final List<T> data;

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public interface OnRecyclerViewItemClickListener {
        public void onItemClick(View view, int position);
    }

    /**
     * 创建 BaseQuickAdapter
     * @param context
     * @param layoutResId 视图id
     */
    public BaseQuickAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    /**
     * 创建 BaseQuickAdapter
     *
     * @param context     The context.
     * @param layoutResId 视图id
     * @param data        数据
     */
    public BaseQuickAdapter(Context context, int layoutResId, List<T> data) {
        this.data = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
        this.context = context;
        this.layoutResId = layoutResId;
    }

    /**
     * 移除指定项
     * @param position
     */
    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);

    }

    /**
     * 添加数据
     * @param position
     * @param item
     */
    public void add(int position, T item) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    /**
     * 添加数据
     * @param item
     */
    public void add(T item) {
        data.add(item);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param list
     */
    public void addAll(List<T> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }
    /**
     * 清楚数据
     */
    public void clear() {
        if (data != null && !data.isEmpty())
            data.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                layoutResId, parent, false);
        return new BaseViewHolder(context, item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
        convert(baseViewHolder, data.get(position));
        if (onRecyclerViewItemClickListener != null) {
            baseViewHolder.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onItemClick(v, position);
                }
            });
        }
    }
    /**
     * 实现此方法 进行视图操作
     *
     * @param helper holder
     * @param item   数据
     */
    protected abstract void convert(BaseViewHolder helper, T item);


    @Override
    public long getItemId(int position) {
        return position;
    }
}
