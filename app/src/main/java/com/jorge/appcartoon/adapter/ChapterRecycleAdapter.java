package com.jorge.appcartoon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jorge.appcartoon.R;
import com.jorge.appcartoon.bean.CartChapter;
import com.jorge.appcartoon.bean.Chapter;
import com.jorge.appcartoon.util.UIUtils;

/**
 * 章节适配器 RecycleView
 *
 * @author：jorge on 2015/11/18 12:19
 */
public class ChapterRecycleAdapter extends RecyclerView.Adapter<ChapterRecycleAdapter.ChapterHolder> implements View.OnClickListener {

    /**
     * 章节个数
     */
    private int count;
    private CartChapter mChapter;
    /**
     * 是否展示全部item
     */
    private boolean showType;

    private int  default_chapters;
    /**
     * @param chapter :章节 对象
     * @param showAll ：是否显示全部章节 、默认 false
     */
    public ChapterRecycleAdapter(CartChapter chapter, boolean showAll) {
        mChapter = chapter;
        count = mChapter.data.size();
        showType = showAll;

        default_chapters= UIUtils.getInteger(R.integer.default_chapters_show);
    }

    /**
     * 对点击的监听
     */
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    /**
     * 章节点击事件接口
     */
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public ChapterRecycleAdapter.ChapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = UIUtils.inflate(R.layout.item_ins_chapter);
        ChapterHolder holder = new ChapterHolder(view);
        view.setOnClickListener(this);
        return holder;
    }
    @Override
    public void onBindViewHolder(ChapterRecycleAdapter.ChapterHolder holder, int position) {
        if(position==count){
            holder.tv1.setText("+++");
            holder.itemView.setTag(position);
            return;
        }
        Chapter chapter = mChapter.data.get(position);
        String chapter_title = chapter.chapter_title;
        holder.tv1.setText(chapter_title);
        holder.itemView.setTag(position);
        if (!showType) {
            if (count >= 12) {
                if (position == 11) {
                    holder.tv1.setText("...");
                    holder.itemView.setTag(position);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (showType) {
            //添加一个额外的矩形，作为收起。
            return count+1;
        } else {
            if (0 < count && count <= default_chapters) {
                return count;
            } else if (count > default_chapters) {
                return default_chapters;
            } else {
                return 0;
            }
        }
    }

    /**
     * 是否显示全部
     *
     * @param type
     */
    public void setShowType(boolean type) {
        showType = type;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    /**
     * 设置监听
     *
     * @param listener：监听器
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    // 章节 item
    public class ChapterHolder extends RecyclerView.ViewHolder {
        TextView tv1;

        public ChapterHolder(View view) {
            super(view);
            tv1 = (TextView) view.findViewById(R.id.tv_1);
        }
    }
}
