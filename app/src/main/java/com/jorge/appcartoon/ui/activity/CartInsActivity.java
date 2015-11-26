package com.jorge.appcartoon.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.jorge.appcartoon.BaseActivity;
import com.jorge.appcartoon.R;
import com.jorge.appcartoon.ThreadManager;
import com.jorge.appcartoon.adapter.ChapterRecycleAdapter;
import com.jorge.appcartoon.bean.CartComment;
import com.jorge.appcartoon.bean.CartInstruction;
import com.jorge.appcartoon.bean.Chapter;
import com.jorge.appcartoon.bean.CommentInfo;
import com.jorge.appcartoon.http.ApiUtil;
import com.jorge.appcartoon.http.protocol.CartInsProtocol;
import com.jorge.appcartoon.util.DateFormatUtil;
import com.jorge.appcartoon.util.ImageLoaderHelper;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.util.ViewUtils;
import com.jorge.appcartoon.widget.MeasureGridLayoutManager;
import com.jorge.appcartoon.widget.RippleLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 漫画介绍页
 *
 * @author：Jorge on 2015/11/16 10:13
 */
public class CartInsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.layout1)
    RippleLayout layout1;
    @Bind(R.id.scroll_view)
    ScrollView scrollView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_cover)
    ImageView ivCover;
    @Bind(R.id.iv_copyright)
    ImageView ivCopyright;
    @Bind(R.id.tv_author)
    TextView tvAuthor;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_hot)
    TextView tvHot;
    @Bind(R.id.tv_subscribe)
    TextView tvSubscribe;
    @Bind(R.id.tv_update_time)
    TextView tvUpdateTime;
    @Bind(R.id.ll_tab)
    LinearLayout llTab;
    @Bind(R.id.tv_reverse_order)
    TextView tvReverseOrder;


    /**评论的父布局*/
    @Bind(R.id.rl_parent_1)
    RelativeLayout rl_parent_1;
    @Bind(R.id.rl_parent_2)
    RelativeLayout rl_parent_2;

    /**评论头像*/
    @Bind(R.id.profile_image_1)
    CircleImageView profileImage_1;
    @Bind(R.id.profile_image_2)
    CircleImageView profileImage_2;
    /**评论昵称*/
    @Bind(R.id.tv_nick_1)
    TextView tvNick_1;
    @Bind(R.id.tv_nick_2)
    TextView tvNick_2;
    /**评论内容*/
    @Bind(R.id.tv_content_1)
    TextView tvContent_1;
    @Bind(R.id.tv_content_2)
    TextView tvContent_2;
    /**评论时间*/
    @Bind(R.id.tv_time_1)
    TextView tvTime_1;
    @Bind(R.id.tv_time_2)
    TextView tvTime_2;
    /**查看全部*/
    @Bind(R.id.tv_whole_1)
    TextView tvWhole_1;
    @Bind(R.id.tv_whole_2)
    TextView tvWhole_2;

    @Bind(R.id.loading_view)
    View loadingView;
    @Bind(R.id.tv_brief_des)
    TextView tvBriefDes;
    @Bind(R.id.tv_des_toggle)
    TextView tvDesToggle;
    @Bind(R.id.tv_discuss)
    TextView tvDiscuss;
    @Bind(R.id.tv_more_discuss)
    TextView tvMoreDiscuss;
    @Bind(R.id.recycle)
    RecyclerView recycle;

    /**获取到的作品信息*/
    private CartInstruction cartInstruction ;
    /**描述是否详情*/
    public static boolean hasOpen = false;
    /**是否显示全部章节*/
    private boolean showAllChapter = false;
    /**数据请求协议*/
    private CartInsProtocol cartInsProtocol;
    /**计算刷新次数*/
    private int i = 1;
    /**章节id 集合*/
    private final String INTENT_EXTRA_CHAPTER_IDS="intent_extra_chapter_ids";
    /** 点击的当前章节 */
    private final String CHAPTER_POSITION="chapter_position";
    /**章节集合*/
    ArrayList<Integer> chapter_ids=new ArrayList<Integer>();
    @Override
    public void onRefresh() {
        ViewUtils.showView(loadingView);
        ViewUtils.showView(progressbar);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 只取网络数据
                cartInstruction = cartInsProtocol.load(++i, 1);
            }
        };
        ThreadManager.getLongPool().execute(runnable);

        cartInsProtocol.setOnCompleteListener(new CartInsProtocol.CompleteListener() {
            @Override
            public void hasFinishLoading() {
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                        setData();
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
                        swipe.setRefreshing(false);
                        ViewUtils.showView(loadingView);
                        ViewUtils.showView(progressbar);
                    }
                });
            }
        });

    }

    @Override
    public void init() {
        // 根据 comic_id 获取介绍页数据
        String  url= ApiUtil.CART_INS_URL.replace(ApiUtil.REPLACE_COMIC_ID ,String.valueOf(getIntent().getIntExtra(ApiUtil.REPLACE_COMIC_ID, 17149)));
        cartInsProtocol = new CartInsProtocol(url);
        cartInsProtocol.setOnCompleteListener(new CartInsProtocol.CompleteListener() {
            @Override
            public void hasFinishLoading() {
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        setData();
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
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                cartInstruction = cartInsProtocol.load(0,0);
            }
        };
        ThreadManager.getLongPool().execute(runnable);
    }

    public void setData() {

        for(int i=0;i<cartInstruction.chapters.get(0).data.size();i++){
            chapter_ids.add(cartInstruction.chapters.get(0).data.get(i).chapter_id);
        }
        /**作者*/
        tvAuthor.setText("  " + cartInstruction.authors.get(0).tag_name);
        /**设置标题*/
        tvTitle.setText(cartInstruction.title);
        /**热度*/
        tvHot.setText("  " + cartInstruction.hot_num + "");
        /**订阅*/
        tvSubscribe.setText("  " + cartInstruction.subscribe_num + "");
        /**cover 图片*/
        ImageLoaderHelper.getInstance().loadImage(cartInstruction.cover.split("\\?")[0], ivCover);
        /**cover tvUpdateTime 时间*/
        tvUpdateTime.setText("  " + DateFormatUtil.formatTime(DateFormatUtil.format(cartInstruction.last_updatetime)));
        /**tvType  分类*/
        String classify = "";
        for (int i = 0; i < cartInstruction.types.size(); i++) {
            classify += cartInstruction.types.get(i).tag_name + " ";
        }
        tvType.setText("  " + classify.substring(0, classify.length() - 1));
        /**设置描述*/
        tvBriefDes.setText(cartInstruction.description.length() >= UIUtils.getInteger(R.integer.txt_desc_max_length) ? cartInstruction.description.substring(0, UIUtils.getInteger(R.integer.txt_desc_max_length)) + "..." : cartInstruction.description);
        /**设置评论数*/
        tvDiscuss.setText(String.format(UIUtils.getString(R.string.txt_discuss), cartInstruction.comment.comment_count));
        /**更多评论*/
        tvMoreDiscuss.setText(String.format(UIUtils.getString(R.string.txt_more_discuss), cartInstruction.comment.comment_count));
        /**初始化评论*/
        CartComment comment = cartInstruction.comment;
        if (comment.comment_count <= 0) return;
        /**头像*/
        List<CommentInfo> list = comment.latest_comment;
        if(list.size()==2){
            /**两条评论*/
            ViewUtils.showView(rl_parent_1);
            ViewUtils.showView(rl_parent_2);
            CommentInfo commentInfo1 = comment.latest_comment.get(0);
            ImageLoaderHelper.getInstance().loadImage(commentInfo1.avatar.split("\\?")[0], profileImage_1);
            /**昵称*/
            tvNick_1.setText(commentInfo1.nickname);
            /**内容*/
            tvContent_1.setText(commentInfo1.content.length() >= UIUtils.getInteger(R.integer.txt_desc_max_length) ? commentInfo1.content.substring(0, UIUtils.getInteger(R.integer.txt_desc_max_length)) + "..." : commentInfo1.content);
            /**评论时间*/
            tvTime_1.setText(DateFormatUtil.formatTime(DateFormatUtil.format(commentInfo1.createtime)));

            CommentInfo commentInfo2 = comment.latest_comment.get(1);
            ImageLoaderHelper.getInstance().loadImage(commentInfo2.avatar.split("\\?")[0], profileImage_2);
            /**昵称*/
            tvNick_2.setText(commentInfo2.nickname);
            /**内容*/
            tvContent_2.setText(commentInfo2.content.length() >= UIUtils.getInteger(R.integer.txt_desc_max_length) ? commentInfo2.content.substring(0, UIUtils.getInteger(R.integer.txt_desc_max_length)) + "..." : commentInfo2.content);
            /**评论时间*/
            tvTime_2.setText(DateFormatUtil.formatTime(DateFormatUtil.format(commentInfo2.createtime)));

        }else if(list.size()==1){
            ViewUtils.showView(rl_parent_1);
            ViewUtils.hideView(rl_parent_2);
            CommentInfo commentInfo1 = comment.latest_comment.get(1);
            ImageLoaderHelper.getInstance().loadImage(commentInfo1.avatar.split("\\?")[0], profileImage_1);
            /**昵称*/
            tvNick_1.setText(commentInfo1.nickname);
            /**内容*/
            tvContent_1.setText(commentInfo1.content.length() >= UIUtils.getInteger(R.integer.txt_desc_max_length) ? commentInfo1.content.substring(0, UIUtils.getInteger(R.integer.txt_desc_max_length)) + "..." : commentInfo1.content);
            /**评论时间*/
            tvTime_1.setText(DateFormatUtil.formatTime(DateFormatUtil.format(commentInfo1.createtime)));
        }else{
            ViewUtils.hideView(rl_parent_1);
            ViewUtils.hideView(rl_parent_2);
        }
        /**初始化RecycleView*/
        recycle.setLayoutManager(new MeasureGridLayoutManager(this, UIUtils.getInteger(R.integer.chapter_items)));
        mChapterAdapter = new ChapterRecycleAdapter(cartInstruction.chapters.get(0), showAllChapter);
        recycle.setAdapter(mChapterAdapter);
        /**章节 点击*/
        mChapterAdapter.setOnItemClickListener(new ChapterRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == cartInstruction.chapters.get(0).data.size() && showAllChapter) {
                    /**此处表示点击收起章节*/
                    showAllChapter = false;
                    mChapterAdapter.setShowType(showAllChapter);
                    mChapterAdapter.notifyDataSetChanged();
                    return;
                }
                if (position == 11 && !showAllChapter) {
                    /**此处表示点击展开全部章节*/
                    showAllChapter = true;
                    mChapterAdapter.setShowType(showAllChapter);
                    mChapterAdapter.notifyDataSetChanged();
                } else {
                    //当前章节 传递过去、
                    Chapter chapter= cartInstruction.chapters.get(0).data.get(position);
                    Intent intent=new Intent(CartInsActivity.this,EnjoyActivity.class);
                    intent.putIntegerArrayListExtra(INTENT_EXTRA_CHAPTER_IDS, chapter_ids);
                    intent.putExtra(CHAPTER_POSITION, position);
                    intent.putExtra(ApiUtil.REPLACE_COMIC_ID,""+cartInstruction.id);
                    intent.putExtra(ApiUtil.REPLACE_CHAPTER_ID, "" + chapter.chapter_id);
                    UIUtils.startActivity(intent);
                }
            }
        });
    }

    //章节 adapter
    private ChapterRecycleAdapter mChapterAdapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_cart_ins);
        ButterKnife.bind(this);
        /**设置允许滑动退出界面*/

        toolbar.setTitle("");
        /** 设置后在改变不生效*/
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /** 顶部刷新的样式*/
        swipe.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light);

//         Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                String msg = "";
//                switch (menuItem.getItemId()) {
//
//                    case R.id.action_settings:
//                        msg += "Click setting";
//                        break;
//                }
//
//                if(!msg.equals("")) {
//                  UIUtils.showToastSafe(msg);
//                }
//                return true;
//            }};
//        toolbar.setOnMenuItemClickListener(onMenuItemClick);
    }

    @Override
    public void addListener() {
        tvDesToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasOpen) {
                    tvBriefDes.setText(cartInstruction.description.length() >= UIUtils.getInteger(R.integer.txt_desc_max_length) ? cartInstruction.description.substring(0, UIUtils.getInteger(R.integer.txt_desc_max_length)) + "..." : cartInstruction.description);
                    tvDesToggle.setBackgroundResource(R.mipmap.icon_open_btn);
                    hasOpen = false;
                } else {
                    tvBriefDes.setText(cartInstruction.description);
                    tvDesToggle.setBackgroundResource(R.mipmap.icon_close_btn);
                    hasOpen = true;
                }
            }
        });
        swipe.setOnRefreshListener(this);
    }

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

