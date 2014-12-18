package com.example.myapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * 自动切换效果Demo
 */
public class MainActivity extends Activity {

    private AutoScrollViewPager viewPager;
    private List<ImageView> imageViewList;
    private LinearLayout indicator;

    private int[] ids = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_scroll_view_pager_demo);
        init();
    }

    private void init() {
        imageViewList = new ArrayList<ImageView>();

        viewPager = (AutoScrollViewPager) findViewById(R.id.view_pager);
        indicator = (LinearLayout) findViewById(R.id.indicator);

        /** 创建view */
        for (int i = 0; i < ids.length; i++) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ImageView view = new ImageView(this);
            view.setLayoutParams(params);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageResource(ids[i]);
            imageViewList.add(view);

            View v = new View(this);
            v.setBackgroundResource(R.drawable.banner_pagecontrol_normal);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(12, 12);
            layoutParams.setMargins(4, 4, 4, 4);
            if (i == 0) {
                v.setBackgroundResource(R.drawable.banner_pagecontrol_selected);
            }
            indicator.addView(v, layoutParams);
        }

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setInterval(4000);
        viewPager.startAutoScroll();
        viewPager.setCycle(true);
        int pos = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViewList.size();
        viewPager.setCurrentItem(pos);
    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            int pos = position % imageViewList.size();
            Log.d("test", "" + indicator.getChildAt(pos));
            for (int i = 0; i < indicator.getChildCount(); i++) {
                indicator.getChildAt(i).setBackgroundResource(R.drawable.banner_pagecontrol_normal);
            }
            indicator.getChildAt(pos).setBackgroundResource(R.drawable.banner_pagecontrol_selected);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewPager.stopAutoScroll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.startAutoScroll();
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            if (imageViewList.size() <= 3) {
                return;
            }
            container.removeView(imageViewList.get(position % imageViewList.size()));

        }

        @Override
        public int getItemPosition(Object object) {

            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return "111";
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = imageViewList.get(position % imageViewList.size());
            if (imageView.getParent() != null) {
                ViewGroup viewGroup = (ViewGroup) imageView.getParent();
                viewGroup.removeView(imageView);

            }
            container.addView(imageView, 0);
            imageViewList.get(position % imageViewList.size()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "click position : " + position % imageViewList.size(), Toast.LENGTH_SHORT).show();
                }
            });
            return imageViewList.get(position % imageViewList.size());
        }

    };
}
