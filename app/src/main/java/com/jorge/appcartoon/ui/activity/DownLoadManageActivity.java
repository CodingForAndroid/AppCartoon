package com.jorge.appcartoon.ui.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.jorge.appcartoon.BaseActivity;
import com.jorge.appcartoon.R;
import com.jorge.appcartoon.bean.Chapter;
import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.util.UIUtils;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 下载管理Activity
 */
public class DownLoadManageActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 正在下载
     */
    @Bind(R.id.btn_downloading)
    Button btnDownloading;
    /**
     * 下载完成
     */
    @Bind(R.id.btn_finish)
    Button btnFinish;
    @Bind(R.id.listView_finish)
    ListView listViewFinish;
    @Bind(R.id.listView_downloading)
    ListView listViewDownloading;
    /**
     * 当前正在下载
     */
    private boolean downLoadingChecked = true;

    private final String INTENT_EXTRA_PARCELABLE_ARRAYLIST = "intent_extra_parcelable_arraylist";

    private ArrayList<Chapter> chapters;

    @Override
    public void init() {

        chapters = getIntent().getParcelableArrayListExtra(INTENT_EXTRA_PARCELABLE_ARRAYLIST);
        for (int i = 0; i < chapters.size(); i++) {
            LogUtils.e("DownLoadManageActivity:" + chapters.get(i));
        }
//        ApiUtil.BASE_URL + first_letter + "/" + comic_id + "/" + cartChapter.data.get(i).chapter_id + ".zip"
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_down_load_manage);
        ButterKnife.bind(this);

    }

    @Override
    public void addListener() {
        btnDownloading.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        DownloadingAdapter downloadingAdapter = new DownloadingAdapter();
        listViewDownloading.setAdapter(downloadingAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_downloading) {
            btnDownloading.setTextColor(UIUtils.getColor(R.color.white));
            btnFinish.setTextColor(UIUtils.getColor(R.color.blue_middle));
            btnDownloading.setBackgroundResource(R.drawable.shape_rectangle_solid_blue_left_radius);
            btnFinish.setBackgroundResource(R.drawable.shape_rectangle_stroke_blue_middle_right_radius);
            downLoadingChecked = true;
        } else if (v.getId() == R.id.btn_finish) {
            btnDownloading.setTextColor(UIUtils.getColor(R.color.blue_middle));
            btnFinish.setTextColor(UIUtils.getColor(R.color.white));
            btnDownloading.setBackgroundResource(R.drawable.shape_rectangle_stroke_blue_middle_left_radius);
            btnFinish.setBackgroundResource(R.drawable.shape_rectangle_solid_blue_right_radius);
            downLoadingChecked = false;
        }
    }

    @Override
    public void finish() {
        //TODO: maybe some problem ,release butterKnife  here
        ButterKnife.unbind(this);
        super.finish();
    }

    /**
     * ListView的适配器
     */
    private class DownloadingAdapter extends BaseAdapter  {


        @Override
        public int getCount() {
            return chapters.size();
        }

        @Override
        public Object getItem(int position) {
            return chapters.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Chapter chapter = chapters.get(position);
            ViewHolder holder;
            if (convertView == null) {
                convertView = UIUtils.inflate(R.layout.item_downloading_list);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
//            holder.setData(chapter);

            return convertView;
        }

        public class ViewHolder {

            private TextView chapter_name;
            private TextView chapter_size;
            private ProgressBar progressBar;

            public ViewHolder(View view) {
                if (chapter_name == null)
                    chapter_name = (TextView) view.findViewById(R.id.tv_chapter_name);
                chapter_size = (TextView) view.findViewById(R.id.tv_current_size);
                progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
            }

            }
        }


    }

