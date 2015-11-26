package com.jorge.appcartoon.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.jorge.appcartoon.BaseActivity;
import com.jorge.appcartoon.R;
import com.jorge.appcartoon.ThreadManager;
import com.jorge.appcartoon.adapter.ChapterUrlAdapter;
import com.jorge.appcartoon.bean.ChapterDetail;
import com.jorge.appcartoon.http.ApiUtil;
import com.jorge.appcartoon.http.protocol.BaseProtocol;
import com.jorge.appcartoon.http.protocol.CartInsProtocol;
import com.jorge.appcartoon.http.protocol.ChapterDetailProtocol;
import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.util.NetWorkUtil;
import com.jorge.appcartoon.util.TimeUtils;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.util.ViewUtils;
import java.util.ArrayList;
import java.util.List;
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
    @Bind(R.id.loading_view)
    View loadingView;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.layout_toolbar)
    RelativeLayout toobarLayout;
    /**底层信息*/
    @Bind(R.id.tv_current_chapter)
    TextView tvCurrentChapter;
    @Bind(R.id.tv_progress)
    TextView tvProgress;
    @Bind(R.id.tv_net_state)
    TextView tvNetState;
    @Bind(R.id.tv_clock)
    TextView tvClock;
    /**底层页 请求协议*/
    private ChapterDetailProtocol chapterDetailProtocol;
    /**底层页 数据封装*/
    private ChapterDetail chapterDetail;
    /**漫画Url集合*/
    private List<String> page_urls = new ArrayList<String>();
    private boolean showTitle = false;
    private final int MESSAGE_WHAT_SHOW_TOP_BOTTOM = 1 << 0;
    private final int MESSAGE_WHAT_LOAD_MORE=1<<1;
    /**传递的 漫画信息*/
    private  String INTENT_EXTRA_CHAPTER_IDS="intent_extra_chapter_ids";
    /**点击的当前章节*/
    private  String CHAPTER_POSITION="chapter_position";

    /**当前章节的 第几个图片；*/
    private  int current_Item_Index;
    /** 当前章节*/
//    private  int  current_chapter;
    /**传递过来的漫画章节信息 */
   private  ArrayList<Integer> chapter_ids;

    private  String chapter_id;
    /** comic_id */
    private String comic_id;
    /**滑动到最后拉*/
    private boolean theLast=false;
    /**是正向滑动 还是逆向  涉及到 每个话的当前页*/
    private boolean orentation_flag=false;

    /**正在加载的标志，正在加载时，则不要重复加载数据*/
    private boolean isLoading=false;

    @Override
    protected void handleMsg(Message msg) {
        if (!Thread.currentThread().isInterrupted()) {
            int what = msg.what;
            switch (what) {
                case MESSAGE_WHAT_SHOW_TOP_BOTTOM:
                    if (showTitle) {
                        showTitle = false;
                        ViewUtils.hideAlphaView(toobarLayout);
                    } else {
                        showTitle = true;
                        ViewUtils.showAlphaView(toobarLayout);
                    }
                    break;
                case MESSAGE_WHAT_LOAD_MORE:

                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        setSwipeBackEnable(false);
        /**章节集合*/
       chapter_ids=getIntent().getIntegerArrayListExtra(INTENT_EXTRA_CHAPTER_IDS);
        /**z总章节*/
        /**当前章节的 comic_id 与 chapter_id*/
        comic_id= getIntent().getStringExtra(ApiUtil.REPLACE_COMIC_ID);
        chapter_id = getIntent().getStringExtra(ApiUtil.REPLACE_CHAPTER_ID);
        /**组装 url*/
        String chapter_url = ApiUtil.CART_CHAPTER_URL.replace(ApiUtil.REPLACE_COMIC_ID, comic_id)
                .replace(ApiUtil.REPLACE_CHAPTER_ID, chapter_id);
        chapterDetailProtocol = new ChapterDetailProtocol(chapter_url);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                chapterDetail = chapterDetailProtocol.load(0, 0);
            }
        };
        ThreadManager.getLongPool().execute(runnable);

        chapterDetailProtocol.setOnCompleteListener(new BaseProtocol.CompleteListener() {
            @Override
            public void hasFinishLoading() {
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.e("148");
                        LogUtils.e(chapterDetail.toString());
                        setData(chapterDetail, POSITION_TYPE.NO);
                        ViewUtils.hideView(loadingView);
                        ViewUtils.hideView(progressbar);
                    }
                });
            }

            @Override
            public void onLoading() {
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        ViewUtils.showView(loadingView);
                        ViewUtils.showView(progressbar);
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

    private int lastPosition;
    @Override
    public void addListener() {
        //标题左上角的后退键
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (theLast) {
                    if(orentation_flag){
                        position= position -lastPosition;
                        tvProgress.setText(String.valueOf((position + 1) + "/" + chapterDetail.picnum));
                    }else{
                        position= position -lastPosition;
                        tvProgress.setText(String.valueOf((position -1) + "/" + chapterDetail.picnum));
                    }

                }else{

                    if(orentation_flag){
                        tvProgress.setText(String.valueOf((position + 1) + "/" + chapterDetail.picnum));
                    }else{
                        tvProgress.setText(String.valueOf((position - 1) + "/" + chapterDetail.picnum));
                    }
                }
        }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    // 滑动
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        theLast = false;
                        break;
                    // 滑动停止
                    case ViewPager.SCROLL_STATE_SETTLING:
                        theLast = true;
                        break;
                    // 空闲状态
                    case ViewPager.SCROLL_STATE_IDLE:
                        loadNext();
                        loadPre();
                        theLast = true;
                        break;
                }
            }

        });

    }

    /**
     *加载前一话
     */
    private void loadPre() {
        orentation_flag=true;
        if(mViewPager.getCurrentItem()==0&&!theLast){

            lastPosition=    mViewPager.getAdapter()
                    .getCount();

            Toast.makeText(EnjoyActivity.this, "已经是第一页",
                    Toast.LENGTH_LONG).show();

            for (int i = 0; i < chapter_ids.size(); i++) {
                String current_chapter_id = chapter_ids.get(i).toString();
                if (chapter_id.equals(current_chapter_id) && i+1 <chapter_ids.size()) {
                    chapter_id = String.valueOf(chapter_ids.get(i + 1));
                    String chapter_url = ApiUtil.BASE_URL + "chapter/" + comic_id + "/" + chapter_id + ".json";
                    final ChapterDetailProtocol newProtocol = new ChapterDetailProtocol(chapter_url);
                    newProtocol.setUrl(chapter_url);
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            chapterDetail = newProtocol.load(0, 0);
                        }
                    };
                    ThreadManager.getLongPool().execute(runnable);
                    newProtocol.setOnCompleteListener(new BaseProtocol.CompleteListener() {
                        @Override
                        public void hasFinishLoading() {
                            UIUtils.post(new Runnable() {
                                @Override
                                public void run() {
                                    LogUtils.e("275");
                                    setData(chapterDetail, POSITION_TYPE.PRE);
                                    ViewUtils.hideView(loadingView);
                                    ViewUtils.hideView(progressbar);
                                }
                            });
                        }

                        @Override
                        public void onLoading() {
                            UIUtils.post(new Runnable() {
                                @Override
                                public void run() {
                                    ViewUtils.showView(loadingView);
                                    ViewUtils.showView(progressbar);
                                }
                            });
                        }
                    });
                    theLast = false;
                    return;
                }
            }
        }
    }

    /**
     * 加载下一话
     */
    private void loadNext() {
        orentation_flag=false;
        if (mViewPager.getCurrentItem() == mViewPager.getAdapter()
                .getCount() - 1 && !theLast) {
            lastPosition=    mViewPager.getAdapter()
                    .getCount();
            Toast.makeText(EnjoyActivity.this, "已经是最后一页",
                    Toast.LENGTH_LONG).show();

            for (int i = 0; i < chapter_ids.size(); i++) {
                String current_chapter_id = chapter_ids.get(i).toString();
                // 找到当前 chapter_id 的位置
                if (chapter_id.equals(current_chapter_id) && i > 0) {
                    chapter_id = String.valueOf(chapter_ids.get(i - 1));
                    String chapter_url = ApiUtil.BASE_URL + "chapter/" + comic_id + "/" + chapter_id + ".json";
                        final ChapterDetailProtocol newProtocol = new ChapterDetailProtocol(chapter_url);
                    newProtocol.setUrl(chapter_url);

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            chapterDetail = newProtocol.load(0, 0);
                        }
                    };
                    ThreadManager.getLongPool().execute(runnable);


                    newProtocol.setOnCompleteListener(new BaseProtocol.CompleteListener() {
                        @Override
                        public void hasFinishLoading() {
                            UIUtils.post(new Runnable() {
                                @Override
                                public void run() {
                                    LogUtils.e("336");
                                    setData(chapterDetail ,POSITION_TYPE.NEXT);
                                    ViewUtils.hideView(loadingView);
                                    ViewUtils.hideView(progressbar);
                                }
                            });
                        }

                        @Override
                        public void onLoading() {
                            UIUtils.post(new Runnable() {
                                @Override
                                public void run() {
                                    ViewUtils.showView(loadingView);
                                    ViewUtils.showView(progressbar);
                                }
                            });
                        }
                    });
                    theLast = false;
                    return;
                }

            }
        }
    }

    private  ChapterUrlAdapter chapterUrlAdapter;


    /**
     * 设置数据
     * @param chapterDetail ： 动漫详情
     * @param type ： 当滑动到 开始或者最后，切换到前一张 或 下一章的时候 判断 数据加载到 原集合的 头部 还是尾部
     */
    public void setData(ChapterDetail chapterDetail ,POSITION_TYPE type) {
        // 拿到当前章节的小说
        if(null==chapterUrlAdapter){
            LogUtils.e("page_urls==null?:    "+(null==page_urls)+"          ( chapterDetail.page_url)==null: "+(null==chapterDetail.page_url));
            page_urls.addAll(chapterDetail.page_url);
            chapterUrlAdapter = new ChapterUrlAdapter(page_urls, handler);
            mViewPager.setAdapter(chapterUrlAdapter);
        }

        switch (type){
            case NO:
                break;
            case PRE:
                chapterUrlAdapter.updateList(chapterDetail.page_url, 1);
                mViewPager.setCurrentItem(chapterDetail.page_url.size()-1);
                break;
            case NEXT:
                chapterUrlAdapter.updateList(chapterDetail.page_url,0);
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                break;
        }

        // 初始化底部信息
        tvCurrentChapter.setText( chapterDetail.title);
        tvClock.setText(TimeUtils.currentTime());
        tvProgress.setText(String.valueOf("1/"+chapterDetail.picnum));
        tvNetState.setText(NetWorkUtil.checkNetworkType(UIUtils.getContext()));
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


    enum POSITION_TYPE{
        /**不区分*/
        NO,
        /** previous */
        PRE,
        /** NEXT */
        NEXT
    }

}
