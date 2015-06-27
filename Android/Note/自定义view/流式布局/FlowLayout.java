package com.example.hottag;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {

	public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlowLayout(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		// 在at_most模式下我们自己计算的高度和宽度
		int width = 0;
		int height = 0;

		// 每一行的宽高
		int lineWidth = 0;
		int lineHeight = 0;

		int childCount = getChildCount();

		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			MarginLayoutParams lp = (MarginLayoutParams) child
					.getLayoutParams();
			int childWidth = child.getMeasuredWidth() + lp.leftMargin
					+ lp.rightMargin;
			int childHeight = child.getMeasuredHeight() + lp.topMargin
					+ lp.bottomMargin;

			if (lineWidth + childWidth > widthSize) {
				width = Math.max(width, lineWidth);
				lineWidth = childWidth;
				height += lineHeight;
				lineHeight = childHeight;
			} else {
				lineWidth += childWidth;
				lineHeight = Math.max(lineHeight, childHeight);
			}

			if (i == childCount - 1) {
				width = Math.max(width, lineWidth);
				height += lineHeight;
			}
		}

		setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize
				: width, heightMode == MeasureSpec.EXACTLY ? heightSize
				: height);
		 Log.i("Tag", getMeasuredWidth()+"");
		 Log.i("Tag", getMeasuredHeight()+"");
	}

	private List<List<View>> mAllViews = new ArrayList<>();
	private List<Integer> mLineHeight = new ArrayList<Integer>();

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mAllViews.clear();
		mLineHeight.clear();

		int width = getWidth();
		int lineWidth = 0;
		int lineHeight = 0;
		List<View> lineViews = new ArrayList<View>();
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			MarginLayoutParams lp = (MarginLayoutParams) child
					.getLayoutParams();
			int childWidth = child.getMeasuredWidth();
			int childHeight = child.getMeasuredHeight();
			// 换行
			if (childWidth+lineWidth + lp.leftMargin + lp.rightMargin > width) {
				mLineHeight.add(lineHeight);
				mAllViews.add(lineViews);
				lineWidth = 0;
				lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
				lineViews = new ArrayList<View>();
			}

			lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
			lineHeight = Math.max(lineHeight, childHeight + lp.topMargin
					+ lp.bottomMargin);
			lineViews.add(child);
		}
		// 处理最后一行
		mLineHeight.add(lineHeight);
		mAllViews.add(lineViews);

		int left = 0;
		int top = 0;
		int lineNum = mAllViews.size();
		
		//取出每一行
		for (int i = 0; i < lineNum; i++) {
			lineViews = mAllViews.get(i);
			lineHeight = mLineHeight.get(i);

			//显示每一行的元素
			for (int j = 0; j < lineViews.size(); j++) {
				View child = lineViews.get(j);
				if (child.getVisibility() == View.GONE)
					continue;

				MarginLayoutParams lp = (MarginLayoutParams) child
						.getLayoutParams();
				int lpo = left + lp.leftMargin;
				int rpo = lpo + child.getMeasuredWidth();
				int tpo = top + lp.topMargin;
				int bpo = tpo + child.getMeasuredHeight();
				child.layout(lpo, tpo, rpo, bpo);
				left += child.getMeasuredWidth() + lp.leftMargin
						+ lp.rightMargin;
			}

			left = 0;
			top += lineHeight;
		}
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

}
