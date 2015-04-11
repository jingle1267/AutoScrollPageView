AutoScrollPageView
==================

实现ViewPager自动播放，循环滚动的效果及使用。
解决当少于4页的时候，有的页面无法显示的bug;

使用方法：
---------

***1.导入library源码***

***2.调用AutoScrollPageView和设置参数***
  ```xml
<com.ihongqiqu.view.lib.AutoScrollViewPager
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
```java
@Override
public void onCreate(Bundle savedInstanceState) {
    // TODO setContentView and so on
    
    AutoScrollViewPager viewPager = (AutoScrollViewPager) findViewById(R.id.view_pager);
    AutoScrollViewPagerAdapter pagerAdapter = new AutoScrollViewPagerAdapter(MainActivity.this) {
        @Override
        public List<View> buildViews() {
            return addView();
        }
    };
    viewPager.setAdapter(pagerAdapter);
    viewPager.start();
        
    // TODO others
}
```
```java
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
```
```java
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
```
 
***3.设置指示器（重要）***

```java
// 设置AutoScrollViewPager时添加监听
viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
```

```java
public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            // 重要：当前真正的位置需要转化 下面pos变量就是真实的位置，可以直接使用pos，不能使用position
            int pos = position % pagerAdapter.getRealPageCount();
            // TODO 根据当前位置pos来进行相应的操作
            
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
```

PS: Demo中暂未使用Android-ViewPagerIndicator

开发者(Developer)
----------------

* [Zhenguo Jin](http://ihongqiqu.com) - <jinzhenguo1990@gmail.com>


License
-------

    Copyright 2014 Zhenguo Jin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
