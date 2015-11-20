package com.jorge.appcartoon.ui.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * @author：Jorge on 2015/11/10 15:06
 */
public class BaseFragmentActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
