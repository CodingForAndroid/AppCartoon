package com.jorge.appcartoon.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jorge.appcartoon.BaseActivity;
import com.jorge.appcartoon.R;
import com.jorge.appcartoon.ThreadManager;
import com.jorge.appcartoon.bean.ChapterDetail;
import com.jorge.appcartoon.http.ApiUtil;
import com.jorge.appcartoon.http.protocol.CartInsProtocol;
import com.jorge.appcartoon.http.protocol.ChapterDetailProtocol;
import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.util.ViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 看动漫页面
 */
public class EnjoyActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    private  ChapterDetailProtocol chapterDetailProtocol;
    private  ChapterDetail chapterDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void init() {
        setSwipeBackEnable(false);
        //组装 url
        String chapter_url = ApiUtil.CART_CHAPTER_URL.replace(ApiUtil.REPLACE_COMIC_ID, getIntent().getStringExtra(ApiUtil.REPLACE_COMIC_ID))
                .replace(ApiUtil.REPLACE_CHAPTER_ID, getIntent().getStringExtra(ApiUtil.REPLACE_CHAPTER_ID));
         chapterDetailProtocol=new ChapterDetailProtocol(chapter_url);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                chapterDetail =  chapterDetailProtocol.load(0,false);
            }
        };
        ThreadManager.getLongPool().execute(runnable);

        chapterDetailProtocol.setOnCompleteListener(new CartInsProtocol.CompleteListener() {
            @Override
            public void hasFinishLoading() {
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
//                        setData();
//                        ViewUtils.hideView(loadingView);
//                        ViewUtils.hideView(progressbar);
                    }
                });
            }

            @Override
            public void onLoading() {
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
//                        ViewUtils.showView(loadingView);
//                        ViewUtils.showView(progressbar);
                    }
                });

            }
        });
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_enjoy);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        /** 设置后在改变不生效*/
        setSupportActionBar(toolbar);

    }

    @Override
    public void addListener() {
        //标题左上角的后退键
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
