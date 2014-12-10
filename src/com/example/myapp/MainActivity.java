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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * 自动切换效果Demo
 */
public class MainActivity extends Activity {

    private AutoScrollViewPager viewPager;
    private List<ImageView> imageViewList;
    private ArrayList<RadioButton> radioButtonList;
    private RadioGroup radioGroup;

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

        radioButtonList = new ArrayList<RadioButton>();
        RadioButton radioButton1 = new RadioButton(this);
        radioButton1.setButtonDrawable(R.drawable.radio_button);
        RadioButton radioButton2 = new RadioButton(this);
        radioButton2.setButtonDrawable(R.drawable.radio_button);
        RadioButton radioButton3 = new RadioButton(this);
        radioButton3.setButtonDrawable(R.drawable.radio_button);
        RadioButton radioButton4 = new RadioButton(this);
        radioButton4.setButtonDrawable(R.drawable.radio_button);
        radioButtonList.add(radioButton1);
        radioButtonList.add(radioButton2);
        radioButtonList.add(radioButton3);
        radioButtonList.add(radioButton4);

        radioGroup = (RadioGroup) findViewById(R.id.indicator);
        radioGroup.addView(radioButton1, 0);
        radioGroup.addView(radioButton2, 1);
        radioGroup.addView(radioButton3, 2);
        radioGroup.addView(radioButton4, 3);

        Log.d("test", "radio button count : " + radioGroup.getChildCount());
    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            Log.d("test", "" + radioGroup.getChildAt(position));
            ((RadioButton) radioGroup.getChildAt(position)).setChecked(true);
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
        // stop auto scroll when onPause
        viewPager.stopAutoScroll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // start auto scroll when onResume
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
