package com.example.cpp.weather;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    VerticalViewPager verticalViewPager;
    ArrayList<View> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.add(LayoutInflater.from(this).inflate(R.layout.vp_1, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.vp_2, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.vp_3, null));


        verticalViewPager = (VerticalViewPager) findViewById(R.id.viewPager);
        verticalViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Override
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((VerticalViewPager) arg0).removeView(list.get(arg1));
            }


            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(list.get(position), 0);
                return list.get(position);
            }
        });
    }


}
