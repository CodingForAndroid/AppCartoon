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
import com.jorge.appcartoon.bean.DownloadInfo;
import com.jorge.appcartoon.http.ApiUtil;
import com.jorge.appcartoon.manager.DownloadManager;
import com.jorge.appcartoon.util.FileUtils;
import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.util.UIUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    private final String INTENT_EXTRA_FIRST_LETTER = "intent_extra_first_letter";
    private final String INTENT_EXTRA_COMIC_ID = "intent_extra_comic_id";

    private ArrayList<Chapter> chapters;
    int  comic_id;
    String   first_letter;
    @Override
    public void init() {
        comic_id = getIntent().getIntExtra(INTENT_EXTRA_COMIC_ID, 0);
        first_letter = getIntent().getStringExtra(INTENT_EXTRA_FIRST_LETTER);
        chapters = getIntent().getParcelableArrayListExtra(INTENT_EXTRA_PARCELABLE_ARRAYLIST);
        for (int i = 0; i < chapters.size(); i++) {
            LogUtils.e("DownLoadManageActivity:" + chapters.get(i));
//            ApiUtil.BASE_URL + first_letter + "/" + comic_id + "/" + cartChapter.data.get(i).chapter_id + ".zip"
           String url= ApiUtil.BASE_URL+first_letter+"/"+comic_id+"/"+chapters.get(i).chapter_id+".zip";
            chapters.get(i).setDownload_url(url);
        }
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
    private class DownloadingAdapter extends BaseAdapter implements DownloadManager.DownloadObserver{

        private List<ViewHolder> mDisplayedHolders;

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
            holder.setData(chapter);

            return convertView;
        }

        @Override
        public void onDownloadStateChanged(DownloadInfo info) {
            refreshHolder(info);
        }

        @Override
        public void onDownloadProgressed(DownloadInfo info) {
            refreshHolder(info);
        }

        public class ViewHolder {

            private int mState;
            private float mProgress;
            private DownloadManager mDownloadManager;
            public Chapter mData;
            private TextView chapter_name;
            private TextView chapter_size;
            private ProgressBar progressBar;
            private boolean hasAttached;

            public ViewHolder(View view) {
                if (chapter_name == null)
                    chapter_name = (TextView) view.findViewById(R.id.tv_chapter_name);
                chapter_size = (TextView) view.findViewById(R.id.tv_current_size);
                progressBar = (ProgressBar) view.findViewById(R.id.progressbar);


                progressBar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("mState:173    " + mState);
                        if (mState == DownloadManager.STATE_NONE
                                || mState == DownloadManager.STATE_PAUSED
                                || mState == DownloadManager.STATE_ERROR) {

                            mDownloadManager.download(mData);
                        } else if (mState == DownloadManager.STATE_WAITING
                                || mState == DownloadManager.STATE_DOWNLOADING) {
                            mDownloadManager.pause(mData);
                        } else if (mState == DownloadManager.STATE_DOWNLOADED) {
//						tell2Server();
//                            mDownloadManager.install(mData);
                        }
                    }
                });
            }

            public void setData(Chapter  chapter) {

                if (mDownloadManager == null) {
                    mDownloadManager = DownloadManager.getInstance();

                }
                String filepath = FileUtils.getDownloadDir() + File.separator + chapter.chapter_title + ".zip";

                boolean existsFile = FileUtils.isExistsFile(filepath);
                if (existsFile) {
                    int fileSize = FileUtils.getFileSize(filepath);

                    if (chapter.filesize == fileSize) {
                        DownloadInfo downloadInfo = DownloadInfo.clone(chapter);
                        downloadInfo.setCurrentSize(chapter.filesize);
                        downloadInfo.setHasFinished(true);
                        mDownloadManager.setDownloadInfo(chapter.chapter_id, downloadInfo);
                    }
//					else if(fileSize>0){
//						DownloadInfo downloadInfo = DownloadInfo.clone(data);
//						downloadInfo.setCurrentSize(data.getSize());
//						downloadInfo.setHasFinished(false);
//						mDownloadManager.setDownloadInfo(data.getId(),downloadInfo );
//					}

                }

                DownloadInfo downloadInfo = mDownloadManager.getDownloadInfo(chapter.chapter_id);
                if (downloadInfo != null) {

                    mState = downloadInfo.getDownloadState();
                    mProgress = downloadInfo.getProgress();
                } else {

                    mState = DownloadManager.STATE_NONE;
                    mProgress = 0;
                }
                this.mData = chapter;
                refreshView();
            }


            public Chapter getData() {
                return mData;
            }

            public void refreshView() {
//                linearLayout.removeAllViews();
                Chapter info = getData();
                chapter_name.setText(info.chapter_title);
//                textView02.setText(FileUtils.FormetFileSize(info.getSize()));
                chapter_size.setText(FileUtils.FormetFileSize(info.filesize));
//                textView03.setText(info.getDes());
//                textView04.setText(info.getDownloadNum() + "下载量");
//                finalBitmap.display(imageView_icon, info.getIconUrl());


                if (false) {
//				mState = DownloadManager.STATE_READ;
//                    textView02.setVisibility(View.GONE);
                } else {
                    String path = FileUtils.getDownloadDir() + File.separator + info.chapter_id + ".zip";
                    hasAttached = FileUtils.isValidAttach(path, false);

                    DownloadInfo downloadInfo = mDownloadManager.getDownloadInfo(info
                            .chapter_id);
                    if (downloadInfo != null && hasAttached) {
                        if (downloadInfo.isHasFinished()) {

                            mState = DownloadManager.STATE_DOWNLOADED;
                        } else {
                            mState = DownloadManager.STATE_PAUSED;

                        }

                    } else {
                        mState = DownloadManager.STATE_NONE;
                        if (downloadInfo != null) {
                            downloadInfo.setDownloadState(mState);
                        }
                    }
                }

                refreshState(mState, mProgress);
            }


            public void refreshState(int state, float progress) {
                mState = state;
                mProgress = progress;
                switch (mState) {
                    case DownloadManager.STATE_NONE:
//                        button.setText(R.string.app_state_download);
                        break;
                    case DownloadManager.STATE_PAUSED:
//                        button.setText(R.string.app_state_paused);
                        break;
                    case DownloadManager.STATE_ERROR:
//                        button.setText(R.string.app_state_error);
                        break;
                    case DownloadManager.STATE_WAITING:
//                        button.setText(R.string.app_state_waiting);
                        break;
                    case DownloadManager.STATE_DOWNLOADING:
//                        button.setText((int) (mProgress * 100) + "%");
                        progressBar.setProgress((int)(mProgress * 100));
                        break;
                    case DownloadManager.STATE_DOWNLOADED:
//                        button.setText(R.string.app_state_downloaded);
                        break;
//			case DownloadManager.STATE_READ:
//				button.setText(R.string.app_state_read);
//				break;
                    default:
                        break;
                }
            }

            }

        public List<DownloadingAdapter.ViewHolder> getDisplayedHolders() {
            synchronized (mDisplayedHolders) {
                return new ArrayList<DownloadingAdapter.ViewHolder>(mDisplayedHolders);
            }
        }

        private void refreshHolder(final DownloadInfo info) {
            List<DownloadingAdapter.ViewHolder> displayedHolders = getDisplayedHolders();
            for (int i = 0; i < displayedHolders.size(); i++) {
                final DownloadingAdapter.ViewHolder holder = displayedHolders.get(i);
                Chapter chapter = holder.getData();
                if (chapter.chapter_id == info.getId()) {
                    UIUtils.post(new Runnable() {
                        @Override
                        public void run() {
                            holder.refreshState(info.getDownloadState(),
                                    info.getProgress());
                        }
                    });
                }
            }

        }




        }


    }

