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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_scroll_view_pager_demo);

        viewPager = (AutoScrollViewPager) findViewById(R.id.view_pager);

        imageViewList = new ArrayList<ImageView>();

        ImageView view1, view2, view3, view4;
        view1 = new ImageView(this);
        view2 = new ImageView(this);
        view3 = new ImageView(this);
        view4 = new ImageView(this);
        view1.setImageResource(R.drawable.banner1);
        view2.setImageResource(R.drawable.banner2);
        view3.setImageResource(R.drawable.banner3);
        view4.setImageResource(R.drawable.banner4);
        imageViewList.add(view1);
        imageViewList.add(view2);
        imageViewList.add(view3);
        imageViewList.add(view4);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setInterval(1000);
        viewPager.startAutoScroll();
        viewPager.setCycle(true);
        // viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViewList.size());
        viewPager.setCurrentItem(0);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(12, 12);
        layoutParams.setMargins(4, 4, 4, 4);
        View v1 = new View(this);
        v1.setBackgroundResource(R.drawable.banner_pagecontrol_normal);
        View v2 = new View(this);
        v2.setBackgroundResource(R.drawable.banner_pagecontrol_normal);
        View v3 = new View(this);
        v3.setBackgroundResource(R.drawable.banner_pagecontrol_normal);
        View v4 = new View(this);
        v4.setBackgroundResource(R.drawable.banner_pagecontrol_normal);

        indicator = (LinearLayout) findViewById(R.id.indicator);
        indicator.addView(v1, 0, layoutParams);
        indicator.addView(v2, 1, layoutParams);
        indicator.addView(v3, 2, layoutParams);
        indicator.addView(v4, 3, layoutParams);

        Log.d("test", "radio button count : " + indicator.getChildCount());
    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            Log.d("test", "" + indicator.getChildAt(position));
            for (int i = 0; i < indicator.getChildCount(); i++) {
                indicator.getChildAt(i).setBackgroundResource(R.drawable.banner_pagecontrol_normal);
            }
            indicator.getChildAt(position).setBackgroundResource(R.drawable.banner_pagecontrol_selected);
            Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
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

            return imageViewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(imageViewList.get(position));

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
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViewList.get(position));
            return imageViewList.get(position);
        }

    };
}
