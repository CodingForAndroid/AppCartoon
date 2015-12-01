package com.jorge.appcartoon.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.jorge.appcartoon.util.LogUtils;

/**
 * @author：Jorge on 2015/11/30 10:06
 */
public class OrientationViewPager extends ViewPager {
    private boolean left = false;
    private boolean right = false;
    private boolean isScrolling = false;
    private int lastValue = -1;
    public OrientationViewPager(Context context) {
        super(context);
        init();
    }

    public OrientationViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public  void init(){
        addOnPageChangeListener(listener);
    }

     private ViewPager.OnPageChangeListener listener=new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (isScrolling) {
                if (lastValue > positionOffsetPixels) {
                    // 递减，向右侧滑动
                    right = true;
                    left = false;
                } else if (lastValue < positionOffsetPixels) {
                    // 递减，向右侧滑动
                    right = false;
                    left = true;
                } else if (lastValue == positionOffsetPixels) {
                    right = left = false;
                }
            }
            LogUtils.e(
                    "meityitianViewPager onPageScrolled  last :arg2  ,"
                            + lastValue + ":" + positionOffsetPixels);
            lastValue = positionOffsetPixels;
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == 1) {
                isScrolling = true;
            } else {
                isScrolling = false;
            }

            if (state == 2) {
                right = left = false;
            }

        }
    };

    /**
     * 得到是否向右侧滑动
     * @return true 为右滑动
     */
    public boolean getMoveRight(){
        return right;
    }

    /**
     * 得到是否向左侧滑动
     * @return true 为左做滑动
     */
    public boolean getMoveLeft(){
        return left;
    }
}
