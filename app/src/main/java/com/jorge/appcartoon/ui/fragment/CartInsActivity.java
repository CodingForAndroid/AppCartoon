package com.jorge.appcartoon.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.jorge.appcartoon.BaseActivity;
import com.jorge.appcartoon.R;
import com.jorge.appcartoon.ThreadManager;
import com.jorge.appcartoon.http.ApiUtil;
import com.jorge.appcartoon.http.protocol.CartInsProtocol;
import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.RevealLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 漫画介绍页
 *
 * @author：Jorge on 2015/11/16 10:13
 */
public class CartInsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.layout1)
    RevealLayout layout1;
    @Bind(R.id.scroll_view)
    ScrollView scrollView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    public void onRefresh() {

    }

    @Override
    public void init() {

        int current_id = getIntent().getIntExtra(ApiUtil.COMIC_ID, 17149);
        final CartInsProtocol cartInsProtocol = new CartInsProtocol(current_id);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                cartInsProtocol.load(0, false);
            }
        };
        ThreadManager.getLongPool().execute(runnable);


        cartInsProtocol.setOnCompleteListener(new CartInsProtocol.CompleteListener() {
            @Override
            public void hasFinishLoading() {

            }

            @Override
            public void onLoading() {

            }
        });

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_cart_ins);
        ButterKnife.bind(this);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

         Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String msg = "";
                switch (menuItem.getItemId()) {

                    case R.id.action_settings:
                        msg += "Click setting";
                        break;
                }

                if(!msg.equals("")) {
                  UIUtils.showToastSafe(msg);
                }
                return true;
            }};
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
    }
//    @Override
//    public int getLayoutResId() {
//        return R.layout.activity_cart_ins;
//    }

//    @Override
//    protected void initView() {
//        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
//
//        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
//        swipe.setOnRefreshListener(this);
//        // 顶部刷新的样式
//        swipe.setColorSchemeResources(android.R.color.holo_red_light,
//                android.R.color.holo_green_light,
//                android.R.color.holo_blue_bright,
//                android.R.color.holo_orange_light);
//    }
//
//    @Override
//    public void onRefresh() {
//        mProgressBar.setVisibility(View.VISIBLE);
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        swipe.setRefreshing(false);
//                        mProgressBar.setVisibility(View.INVISIBLE);
//                    }
//                });
//
//            }
//        };
//        timer.schedule(task, 3000);
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}

