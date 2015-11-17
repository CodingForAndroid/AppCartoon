package com.jorge.appcartoon.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.jorge.appcartoon.bean.CartComment;
import com.jorge.appcartoon.bean.CartInstruction;
import com.jorge.appcartoon.http.ApiUtil;
import com.jorge.appcartoon.http.protocol.CartInsProtocol;
import com.jorge.appcartoon.util.DateFormatUtil;
import com.jorge.appcartoon.util.ImageLoaderHelper;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.util.ViewUtils;
import com.jorge.appcartoon.widget.RevealLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * ��������ҳ
 *
 * @author��Jorge on 2015/11/16 10:13
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
    @Bind(R.id.tv_positive_order)
    TextView tvPositiveOrder;
    @Bind(R.id.profile_image)
    CircleImageView profileImage;
    @Bind(R.id.tv_nick)
    TextView tvNick;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_whole)
    TextView tvWhole;
    @Bind(R.id.ll_content)
    RelativeLayout llContent;
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

    //��ȡ������Ʒ��Ϣ
    private CartInstruction cartInsruction;
    //�����Ƿ�����
    public static boolean hasOpen = false;

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
                cartInsruction = cartInsProtocol.load(0, false);
            }
        };
        ThreadManager.getLongPool().execute(runnable);

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

    }

    public void setData() {
        //����
        tvAuthor.setText("  " + cartInsruction.authors.get(0).tag_name);
        //���ñ���
        tvTitle.setText(cartInsruction.title);
        //�ȶ�
        tvHot.setText("  " + cartInsruction.hot_num + "");
        //����
        tvSubscribe.setText("  " + cartInsruction.subscribe_num + "");
        //cover ͼƬ
        ImageLoaderHelper.getInstance().loadImage(cartInsruction.cover.split("\\?")[0], ivCover);
        //tvUpdateTime ʱ��
        tvUpdateTime.setText("  " + DateFormatUtil.formatTime(DateFormatUtil.format(cartInsruction.last_updatetime)));
        //tvType  ����
        String classify = "";
        for (int i = 0; i < cartInsruction.types.size(); i++) {
            classify += cartInsruction.types.get(i).tag_name + " ";
        }
        tvType.setText("  " + classify.substring(0, classify.length() - 1));

        //��������
        tvBriefDes.setText(cartInsruction.description.length() >= UIUtils.getInteger(R.integer.txt_desc_max_length) ? cartInsruction.description.substring(0, UIUtils.getInteger(R.integer.txt_desc_max_length)) + "..." : cartInsruction.description);

        //����������
        tvDiscuss.setText( String.format( UIUtils.getString(R.string.txt_discuss), cartInsruction.comment.comment_count));
        //��������
        tvMoreDiscuss.setText( String.format( UIUtils.getString(R.string.txt_more_discuss), cartInsruction.comment.comment_count));
        //��ʼ������
        CartComment comment=cartInsruction.comment;
       if(comment.comment_count<=0) return;
        //ͷ��
        CartComment.CommentInfo commentInfo=comment.latest_comment.get(0);
        ImageLoaderHelper.getInstance().loadImage(commentInfo.avatar.split("\\?")[0], profileImage);
        //�ǳ�
        tvNick.setText(commentInfo.nickname);
        //����
        tvContent.setText(commentInfo.content.length() >= UIUtils.getInteger(R.integer.txt_desc_max_length) ? commentInfo.content.substring(0, UIUtils.getInteger(R.integer.txt_desc_max_length)) + "..." : commentInfo.content);
        //����ʱ��
        tvTime.setText(DateFormatUtil.formatTime(DateFormatUtil.format(commentInfo.createtime)));
//        tv_whole �鿴ȫ��

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_cart_ins);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        // ���ú� �ڸı䲻��Ч
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
                    tvBriefDes.setText(cartInsruction.description.length() >= UIUtils.getInteger(R.integer.txt_desc_max_length) ? cartInsruction.description.substring(0, UIUtils.getInteger(R.integer.txt_desc_max_length)) + "..." : cartInsruction.description);
                    tvDesToggle.setBackgroundResource(R.mipmap.icon_open_btn);
                    hasOpen = false;
                } else {
                    tvBriefDes.setText(cartInsruction.description);
                    tvDesToggle.setBackgroundResource(R.mipmap.icon_close_btn);
                    hasOpen = true;
                }
            }
        });
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
//        // ����ˢ�µ���ʽ
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

