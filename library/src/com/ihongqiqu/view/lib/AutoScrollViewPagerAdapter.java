package com.ihongqiqu.view.lib;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * AutoScrollViewPager的适配器
 *
 * Created by zhenguo on 4/3/15.
 */
public abstract class AutoScrollViewPagerAdapter extends PagerAdapter {

    private List<View> viewList;
    private int realPageCount = 1;
    private int pageCount = 0;

    private Context context;

    public AutoScrollViewPagerAdapter(Context context) {
        viewList = new ArrayList<>();
        this.context = context;

        List<View> realViews = buildViews();
        if (realViews != null && realViews.size() > 0) {

            this.viewList.addAll(realViews);
            realPageCount = this.viewList.size();
            if (realPageCount == 2) {
                this.viewList.addAll(buildViews());
            }
            pageCount = this.viewList.size();
        }
    }

    /**
     * 创建Views
     * @return
     */
    public abstract List<View> buildViews();

    /**
     * 获取真实的ViewPager大小
     * @return
     */
    public int getRealPageCount () {
        return realPageCount;
    }

    /**
     * 获取转化后的PageCount
     * @return
     */
    public int getPageCount() {
        return pageCount;
    }

    @Override
    public final boolean isViewFromObject(View arg0, Object arg1) {

        return arg0 == arg1;
    }

    @Override
    public final int getCount() {
        if (this.viewList == null || this.viewList.size() == 0) {
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public final void destroyItem(ViewGroup container, int position,
                            Object object) {
        if (viewList.size() <= 3) {
            return;
        }
        container.removeView(viewList.get(position % viewList.size()));

    }

    @Override
    public final int getItemPosition(Object object) {

        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return "111";
    }

    @Override
    public final Object instantiateItem(ViewGroup container, final int position) {
        View view = viewList.get(position % viewList.size());
        if (view.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);
        }
        container.addView(view, 0);
        return view;
    }
}
