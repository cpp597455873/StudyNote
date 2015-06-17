package com.cqupt.cpp.testdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author chen piao piao
 * 这个类的作用是将统一的内容封装起来，子类不必要实现通用的方法。
 */
public abstract class CommomAdapter<T> extends BaseAdapter {

    private Context mContext;
    private int layoutId;
    private List<T> mData;


    public CommomAdapter(Context mContext, int layoutId, List<T> mData) {
        this.mContext = mContext;
        this.layoutId = layoutId;
        this.mData = mData;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, position, layoutId);
        //钩子方法
        setView(viewHolder, mData.get(position));
        return viewHolder.getConverView();
    }

   /**
   * 交由子类实现具体的内特ring绑定
   */
    public abstract void setView(ViewHolder viewHolder, T t);


}
