package com.jorge.appcartoon.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jorge.appcartoon.BaseActivity;
import com.jorge.appcartoon.R;
import com.jorge.appcartoon.bean.CartChapter;
import com.jorge.appcartoon.bean.Chapter;
import com.jorge.appcartoon.http.ApiUtil;
import com.jorge.appcartoon.http.HttpUtil;
import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.util.NetWorkUtil;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.MeasureGridLayoutManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 漫画下载界面
 */
public class DownListActivity extends BaseActivity {

    @Bind(R.id.recycle_down)
    RecyclerView mRecyclerView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_state)
    TextView tvState;
    @Bind(R.id.tv_chapter_count)
    TextView tvChapterCount;
    /**漫画章节信息*/
    private CartChapter cartChapter;
    //    private List<Chapter> list;
    private final String INTENT_EXTRA_PARCELABLE_CARTCHAPTER = "intent_extra_parcelable_cartchapter";
    /**
     * comic_id
     */
    private final String INTENT_EXTRA_COMIC_ID = "intent_extra_comic_id";
    /**
     * 首字母
     */
    private final String INTENT_EXTRA_FIRST_LETTER = "intent_extra_first_letter";

    /**选中要下载的章节*/
    private final String INTENT_EXTRA_PARCELABLE_ARRAYLIST = "intent_extra_parcelable_arraylist";

    /**
     * 漫画id
     */
    private int comic_id;
    /**
     * 首字母
     */
    private String first_letter;

    @Override
    public void init() {
        cartChapter = getIntent().getParcelableExtra(INTENT_EXTRA_PARCELABLE_CARTCHAPTER);
        comic_id = getIntent().getIntExtra(INTENT_EXTRA_COMIC_ID, 0);
        first_letter = getIntent().getStringExtra(INTENT_EXTRA_FIRST_LETTER);
    }

    public void initView() {
        setContentView(R.layout.activity_down_list);
        ButterKnife.bind(this);
        /**初始化标题设置*/
        toolbar.setTitle("");
        /** 设置后在改变不生效*/
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**设置标题*/
        tvTitle.setText(R.string.txt_choose_chapter);
        /** 设置漫画状态*/
        tvState.setText(cartChapter.title);
        /**设置 漫画总章节数*/
        tvChapterCount.setText(String.format(UIUtils.getString(R.string.txt_chapter_count), cartChapter.data.size()));

        DownLoadListAdapter adapter = new DownLoadListAdapter();
        /**设置布局方向 是ListView 还是GridView*/
        mRecyclerView.setLayoutManager(new MeasureGridLayoutManager(this, UIUtils.getInteger(R.integer.chapter_items)));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void addListener() {

    }

    /**
     * ApiUtil.BASE_URL + first_letter + "/" + comic_id + "/" + cartChapter.data.get(i).chapter_id + ".zip"
     * 获取下载列表
     */
    public ArrayList <Chapter> getDownList() {
        ArrayList<Chapter> selectedList = new ArrayList<Chapter>();
        for (int i = 0; i < mRecyclerView.getChildCount(); i++) {
            View view = mRecyclerView.getChildAt(i);
            int tag = (int) view.getTag();
            if (tag == 1) {
                selectedList.add( cartChapter.data.get(i));
            }

        }
        return selectedList;
    }

    private class DownLoadListAdapter extends RecyclerView.Adapter<ListHolder> implements View.OnClickListener {

        /**
         * 初始状态、没有选中
         */
        private int item_init_state = 0;
        /**
         * 当前Item被选中
         */
        private final int item_state_checked = 1;
        /**
         * 当前item未被选中
         */
        private final int item_state_unchecked = 0;
        /**
         * 当前item已经下载过
         */
        private final int item_state_download = 2;

        @Override
        public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = UIUtils.inflate(R.layout.item_ins_chapter);
            ListHolder holder = new ListHolder(view);
            view.setOnClickListener(this);
            return holder;
        }

        @Override
        public void onBindViewHolder(ListHolder holder, int position) {
            Chapter chapter = cartChapter.data.get(position);
            holder.tv1.setText(chapter.chapter_title);
            item_init_state = 0;
            holder.itemView.setTag(item_init_state);
        }

        @Override
        public int getItemCount() {
            return cartChapter.data.size();
        }

        @Override
        public void onClick(View v) {
            item_init_state = (int) v.getTag();
            TextView tv1 = (TextView) v.findViewById(R.id.tv_1);
            if (item_init_state == item_state_checked) {
                /**切换为未被选中状态*/
                tv1.setBackgroundResource(R.drawable.shape_recycle_item);
                item_init_state = item_state_unchecked;
                v.setTag(item_init_state);
            } else if (item_init_state == item_state_unchecked) {
                /**切换到选中状态*/
                tv1.setBackgroundResource(R.drawable.shape_rectangle_blue);
                item_init_state = item_state_checked;
                v.setTag(item_init_state);
            }else{
//                已经下载过
//                shape_rectangle_white
            }
        }

    }


    private class ListHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        public ListHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv_1);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_down:
                ArrayList <Chapter> list = getDownList();
                for (int i = 0; i < list.size(); i++) {
                    LogUtils.e("下载:  " + list.get(i));
                }
                /**跳转到下载界面*/
                Intent  intent=new Intent(DownListActivity.this, DownLoadManageActivity.class);
                intent.putParcelableArrayListExtra(INTENT_EXTRA_PARCELABLE_ARRAYLIST,list);
                UIUtils.startActivity(intent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
