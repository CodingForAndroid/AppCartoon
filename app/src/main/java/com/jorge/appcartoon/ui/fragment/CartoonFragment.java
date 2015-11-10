package com.jorge.appcartoon.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.jorge.appcartoon.R;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.PagerTab;

import butterknife.Bind;

/**
 * 卡通
 * @author：Jorge on 2015/11/10 15:38
 */
public class CartoonFragment extends BaseFragment {
    @Bind(R.id.tabs)
    PagerTab mPageTabs;
    @Bind(R.id.pager)
    ViewPager mPager;

    private   MainPagerAdapter  mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cartoon);
    }

    @Override
    public void initViews() {
        //初始化pager
        mAdapter = new MainPagerAdapter(getFragmentManager());
        mPager.setAdapter(mAdapter);
        //tab的初始化、tab和ViewPager的互相绑定
        mPageTabs.setViewPager(mPager);
        mPageTabs.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    @Override
    public void addListener() {

    }


    /** ViewPager的适配器 */
    public class MainPagerAdapter extends FragmentPagerAdapter {
        private String[] mTabTitle;

        public MainPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            mTabTitle = UIUtils.getStringArray(R.array.tab_names);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitle[position];
        }

        @Override
        public int getCount() {
            return mTabTitle.length;
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createFragment(position);
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
            //ViewPager滑动状态改变的回调
        }

        @Override
        public void onPageScrolled(int index, float offset, int offsetPx) {
            //ViewPager滑动时的回调
        }

        @Override
        public void onPageSelected(int index) {
            // ViewPager页面被选中的回调
            BaseFragment fragment = FragmentFactory.createFragment(index);
            // 当页面被选中 再显示要加载的页面....防止ViewPager提前加载(ViewPager一般加载三个，自己，左一个，右一个)
//            fragment.show();// 调用show方法加载pager里面的数据
        }
    }
}
