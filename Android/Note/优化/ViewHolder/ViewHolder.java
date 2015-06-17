package com.cqupt.cpp.testdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author chenpiaopiao 2015/6/17.
 * 这个类的中用是实现ViewHolder的统一封装，以后所有的Holder使用这一个viewholder就可以了不用多次编写
 */
public class ViewHolder {
    private Context mContext;
    private int layoutId;
    private SparseArray<View> mViews;
    private View converView;
    private int position;

    public ViewHolder(Context mContext, int layoutId, ViewGroup parent, int position) {
        this.mContext = mContext;
        this.layoutId = layoutId;
        this.position = position;
        mViews = new SparseArray();
        converView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        converView.setTag(this);
    }

    /**
    *根据ID获得控件
    */
    public View getView(int id) {
        View v = mViews.get(id);
        if (v == null) {
            v = converView.findViewById(id);
            mViews.put(id, v);
        }
        return v;
    }

    public View getConverView() {
        return converView;
    }

    /**
     *获得一个ViewHolder
     */
    public static ViewHolder get(Context mContext, View convertView, ViewGroup parent, int position, int layoutId) {
        if (convertView == null) {
            return new ViewHolder(mContext, layoutId, parent, position);
        } else {
            ViewHolder viewHoler = (ViewHolder) convertView.getTag();
            viewHoler.position = position;
            return viewHoler;
        }
    }

    /**
     * 这里采用了链式编程的方法，给TextView设置文本，同理还可以给其他的view设置其他的值
     */
    public ViewHolder setText(int viewId,String text){
        TextView tv = (TextView) getView(viewId);
        tv.setText(text);
        return  this;
    }

}