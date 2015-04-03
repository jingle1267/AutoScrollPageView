package com.ihongqiqu.view.viewpager.sample;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.ihongqiqu.view.lib.AutoScrollViewPager;
import com.ihongqiqu.view.lib.AutoScrollViewPagerAdapter;

/**
 * 自动切换效果Demo
 */
public class MainActivity extends Activity {

    private AutoScrollViewPager viewPager;
    private AutoScrollViewPagerAdapter pagerAdapter;
    private LinearLayout indicator;

    private int[] ids = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3/*, R.drawable.banner4*/};

    private int realPageCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_scroll_view_pager_demo);
        init();
    }

    private void init() {

        viewPager = (AutoScrollViewPager) findViewById(R.id.view_pager);
        indicator = (LinearLayout) findViewById(R.id.indicator);

        addIndicators();

        pagerAdapter = new AutoScrollViewPagerAdapter() {

            @Override
            public List<View> buildViews() {
                return addView();
            }
        };

        viewPager.setAdapter(pagerAdapter);
        realPageCount = pagerAdapter.getRealPageCount();

        viewPager.setInterval(2000);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

    }

    private List<View> addView() {
        List<View> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ImageView view = new ImageView(this);
            view.setLayoutParams(params);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageResource(ids[i]);
            view.setTag(i);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "position : " + v.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
            list.add(view);
        }
        return list;
    }

    private void addIndicators() {
        for (int i = 0; i < ids.length; i++) {
            View v = new View(this);
            v.setBackgroundResource(R.drawable.banner_pagecontrol_normal);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(12, 12);
            layoutParams.setMargins(4, 4, 4, 4);
            if (i == 0) {
                v.setBackgroundResource(R.drawable.banner_pagecontrol_selected);
            }
            indicator.addView(v, layoutParams);
        }
    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            int pos = position % realPageCount;
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
        viewPager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.onResume();
    }


}