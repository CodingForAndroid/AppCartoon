package com.jorge.appcartoon.adapter;

import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import com.jorge.appcartoon.ui.activity.EnjoyActivity;
import com.jorge.appcartoon.util.ImageLoaderHelper;
import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.util.UIUtils;

import java.util.Collections;
import java.util.List;

/**
 * 图片适配器
 * @author：Jorge on 2015/11/20 20:51
 */

// 图片适配器
public class ChapterUrlAdapter extends ViewPagerAdapter implements View.OnClickListener {

    private int MESSAGE_WHAT_SHOW_TOP_BOTTOM=1 << 0;
    private int MESSAGE_WHAT_LOAD_MORE=2;
    private List<String> mList;
    private Handler mHandler;
    public ChapterUrlAdapter(List<String> pages,Handler handler) {
        mList = pages;
        mHandler=handler;
    }

    @Override
    protected View getView(View convertView, int position) {
        if (convertView == null) {
            convertView = new ImageView(UIUtils.getContext());
        }
        if(position==mList.size()-1){
            mHandler.sendEmptyMessage(MESSAGE_WHAT_LOAD_MORE);
        }
        convertView.setOnClickListener(this);
        ImageLoaderHelper.getInstance().loadImage(mList.get(position), (ImageView) convertView);
        return convertView;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void clearList(){
        mList.clear();
    }

    /**
     *  加载数据
     * @param newList： 新数据
     * @param type：0 1 2 分别代表  0 代表 自然顺序添加  1 代表添加在 起始位置
     */
    public void updateList(List<String>newList,int type){
//        clearList();
        for(int i=0;i<newList.size();i++){
            LogUtils.e("NEWLIST:   ======================== "+newList.get(i));
        }
//        mList.clear();
        switch (type){
            case 0:
                mList.addAll(newList);
                break;
            case 1:
//                Collections.reverse(newList);
                mList.addAll(0,newList);
                break;
        }

        this.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        mHandler.sendEmptyMessage(MESSAGE_WHAT_SHOW_TOP_BOTTOM);
    }

//    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_NONE;
//    }
//
//    @Override
//    public void finishUpdate(View arg0) {}
//
//    @Override
//    public void restoreState(android.os.Parcelable state, ClassLoader loader) {
//
//    };
//
//    @Override
//    public Parcelable saveState() {
//        return null;
//    }
//
//    @Override
//    public void startUpdate(View arg0) {}


}
