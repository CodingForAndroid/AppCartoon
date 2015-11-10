package com.jorge.appcartoon.ui.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author：Jorge on 2015/11/10 15:06
 */
public class BaseFragmentActivity extends FragmentActivity {

    /** 记录处于前台的Activity */
    private static BaseFragmentActivity mForegroundActivity = null;

    public Fragment getFragmentByTag(Class<? extends Fragment> clazz) {
        return getSupportFragmentManager().findFragmentByTag(clazz.getName());
    }

    public Fragment getFragmentByTag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    /** 获取当前处于前台的activity */
    public static BaseFragmentActivity getForegroundActivity() {
        return mForegroundActivity;
    }



    /**
     * setContentView(int layoutResID)
     */

    @Override
    protected void onResume() {
        mForegroundActivity = this;
        super.onResume();
    }

    @Override
    protected void onPause() {
        mForegroundActivity = null;
        super.onPause();
    }

}
