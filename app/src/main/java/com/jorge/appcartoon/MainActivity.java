package com.jorge.appcartoon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;

import com.jorge.appcartoon.ui.activity.BaseFragmentActivity;
import com.jorge.appcartoon.ui.fragment.TabFragment;

/**
 * 主Activity
 */
public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }


    /**
     * 添加tab fragment
     */
    private void initViews() {
        TabFragment tabFragment=new TabFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.content_frame,tabFragment,tabFragment.getClass().getName()).commit();
    }

}
