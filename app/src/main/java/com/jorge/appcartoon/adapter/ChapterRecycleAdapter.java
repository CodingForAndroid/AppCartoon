package com.jorge.appcartoon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jorge.appcartoon.R;
import com.jorge.appcartoon.bean.CartChapter;
import com.jorge.appcartoon.bean.Chapter;
import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.util.UIUtils;

/**
 * �½�������RecycleView
 *
 * @author��Jorge on 2015/11/18 12:19
 */
public class ChapterRecycleAdapter extends RecyclerView.Adapter<ChapterRecycleAdapter.ChapterHolder> implements View.OnClickListener {

    /**
     * �½ڸ���
     */
    private int count;
    private CartChapter mChapter;
    /**
     * �Ƿ�չʾȫ��item
     */
    private boolean showType;

    /**
     * @param chapter :�½� ����
     * @param showAll ���Ƿ���ʾȫ���½� ��Ĭ�� false
     */
    public ChapterRecycleAdapter(CartChapter chapter, boolean showAll) {
        mChapter = chapter;
        count = mChapter.data.size();
        showType = showAll;
    }

    /**
     * �Ե���ļ���
     */
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    /**
     * �½ڵ���¼��ӿ�
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
            //���һ������ľ��Σ���Ϊ����
            return count+1;
        } else {
            if (0 < count && count <= 12) {
                return count;
            } else if (count > 12) {
                return 12;
            } else {
                return 0;
            }
        }

    }

    /**
     * �Ƿ���ʾȫ��
     *
     * @param type
     */
    public void setShowType(boolean type) {
        showType = type;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //ע������ʹ��getTag������ȡ����
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    /**
     * ���ü���
     *
     * @param listener��������
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    // �½� item
    public class ChapterHolder extends RecyclerView.ViewHolder {
        TextView tv1;

        public ChapterHolder(View view) {
            super(view);
            tv1 = (TextView) view.findViewById(R.id.tv_1);
        }
    }
}
