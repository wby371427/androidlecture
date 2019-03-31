package com.viewpager.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tools_define.R;

import static com.example.tools_define.R.drawable.c;
import static com.example.tools_define.R.drawable.selector_dot;

public class ViewPagerActivity extends Activity {
    private MyAdapter myAdapter;
    private ViewPager viewPager;
    private TextView mViewPagerText;
    private int[] imageResIds = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
    };

    private String[] descs = {
            "巩俐不低俗，我就不能低俗",
            "扑树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级",
            "乐视网TV版大派送",
            "热血屌丝的反杀",
    };
    private ImageView[] imageViews = new ImageView[imageResIds.length];
    private View[] dots = new View[imageResIds.length];
    private LinearLayout dotLayout;
    private View currentView;
    private int maxPage = imageResIds.length * 1000 * 100;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int current = viewPager.getCurrentItem();
            current++;
            viewPager.setCurrentItem(current);
            handler.sendEmptyMessageDelayed(0,1000);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);
        viewPager = findViewById(R.id.viewpager);
        dotLayout = findViewById(R.id.root_dot);
        mViewPagerText = findViewById(R.id.viewpager_text);
        createImageViews();
        myAdapter = new MyAdapter();
        viewPager.setAdapter(myAdapter);
        viewPager.setOnPageChangeListener(onPageChangeListener);
        change(0);
        int currentItem = maxPage/2;
        viewPager.setCurrentItem(currentItem);

    }
    private void createImageViews(){
        for (int i=0; i<imageResIds.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageResIds[i]);
            imageViews[i] = imageView;
            createDots(i);
        }
    }
    private void createDots(int i){
        View view = new View(this);
        view.setBackgroundResource(R.drawable.selector_dot);
        dots[i] = view;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(5,5);
        params.rightMargin = 5;
        dots[i].setLayoutParams(params);
        dotLayout.addView(dots[i]);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            change(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            if (i == ViewPager.SCROLL_STATE_IDLE){
                handler.sendEmptyMessageDelayed(0,1000);
            }else {
                handler.removeMessages(0);
            }
        }
    };

    private void change(int i){
        i = i % imageResIds.length;
        mViewPagerText.setText(descs[i]);
        dots[i].setSelected(true);
        if (currentView != null){
            currentView.setSelected(false);
        }
        currentView = dots[i];
    }
    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return maxPage;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            position = position % imageResIds.length;
            container.addView(imageViews[position]);
            return imageViews[position];
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.sendEmptyMessageDelayed(0,1000);
    }

    @Override
    protected void onStop() {
        handler.removeMessages(0);
        super.onStop();
    }
}
