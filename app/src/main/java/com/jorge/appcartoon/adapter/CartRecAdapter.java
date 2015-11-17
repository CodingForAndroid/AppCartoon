package com.jorge.appcartoon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.appcartoon.R;
import com.jorge.appcartoon.bean.CartRecClassify;
import com.jorge.appcartoon.bean.CartWork;
import com.jorge.appcartoon.util.UIUtils;

import java.util.List;

/**
 * 动漫推荐页的内容适配器
 * @author：Jorge on 2015/11/11 18:25
 */
public class CartRecAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    // 根据 item分组
    //0 不显示 就可以了
    private static final int ITEM_ZERO =0;
    //3
    private static final int ITEM_THREE = 3;
    //4
    private static final int ITEM_FOUR = 4;
    //6
    private static final int ITEM_SIX = 6;
    //数据
    private List<CartRecClassify> mDataList;

    public CartRecAdapter( List<CartRecClassify> list){
        mDataList=list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ITEM_ZERO:
                return null;
            case ITEM_THREE:
//                return  new ThreeItemHolder(UIUtils.inflate(R.layout.recycler_three_item));
            case ITEM_FOUR:
//                return new FourItemHolder(UIUtils.inflate(R.layout.recycler_four_item));
            case ITEM_SIX:
//                return new SixItemHolder(UIUtils.inflate(R.layout.recycler_six_item));
        }

        return new ThreeItemHolder(UIUtils.inflate(R.layout.fragment_rec_componet_strong_rec));
//        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CartRecClassify cartRecClassify=  mDataList.get(position + 1);
        if(cartRecClassify==null) return;
        List<CartWork>  resultList=cartRecClassify.data;
        if(null!=resultList&&resultList.size()>0){
            //确保有数据
            switch (resultList.size()){
                case ITEM_ZERO:
                    break;
                case ITEM_THREE:
                    break;
                case ITEM_FOUR:
                    break;
                case ITEM_SIX:
                    break;
            }
        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        // 因为首页的数据全部在这里面，第0个是轮播图的，所以剩下的个数要减一
        return mDataList.size()-1;
    }

    @Override
    public int getItemViewType(int position) {
        // 根据具体item个数去区分item 的类型
       return mDataList.get(position+1).data.size();
    }

    /**
     * 三个的布局Holder
     */
    public class ThreeItemHolder extends RecyclerView.ViewHolder {
        TextView mItemTitle;
        ImageView rightIcon;

        public ThreeItemHolder(View itemView) {
            super(itemView);
            //标题
//            mItemTitle = (TextView) itemView.findViewById(R.id.tv_strongly_title);
//            rightIcon = (ImageView) itemView.findViewById(R.id.tv_strongly_right_ic);
//            //图片
//            rightIcon = (ImageView) itemView.findViewById(R.id.tv_strongly_right_ic);
//            rightIcon = (ImageView) itemView.findViewById(R.id.tv_strongly_right_ic);
//            rightIcon = (ImageView) itemView.findViewById(R.id.tv_strongly_right_ic);


        }
    }

    /**
     * 四个的布局Holder
     */
    public class FourItemHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        ImageView newsIcon;

        public FourItemHolder(View itemView) {
            super(itemView);

        }
    }

    /**
     * 六个的布局Holder
     */
    public class SixItemHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        ImageView newsIcon;

        public SixItemHolder(View itemView) {
            super(itemView);

        }
    }
}
