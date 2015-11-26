package com.jorge.appcartoon.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.jorge.appcartoon.R;
import com.jorge.appcartoon.adapter.BaseHolder;
import com.jorge.appcartoon.adapter.CartRecHolder;
import com.jorge.appcartoon.adapter.DefaultAdapter;
import com.jorge.appcartoon.bean.CartRecClassify;
import com.jorge.appcartoon.http.protocol.CartRecProtocol;
import com.jorge.appcartoon.util.ImageLoaderHelper;
import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.util.SystemUtils;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.BaseListView;
import com.jorge.appcartoon.widget.ImageCycleView;
import com.jorge.appcartoon.widget.LoadingPage;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 漫画——推荐
 *
 * @author：Jorge on 2015/11/10 17:21
 */
public class CartRecFragment extends BaseFragment {

    // 旋转图
    @Bind(R.id.cycleView)
    ImageCycleView carouselImage;
    //首页数据集合
    List<CartRecClassify> list;
    //listview 根据 类型 添加不同的数据,用recycleView 代替listview
    @Bind(R.id.baseListView)
    BaseListView mBaseListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViewsAndEvents() {
        initCarsuelView();
        prepareLayout();
    }

    @Override
    protected LoadingPage.LoadResult load() {
        CartRecProtocol demoProtocol = new CartRecProtocol();
        list = demoProtocol.load(0,0);
        return check(list);
    }

    @Override
    protected View createLoadedView() {
        View view = UIUtils.inflate(R.layout.fragment_recommend);
        ButterKnife.bind(this, view);
        return view;
    }

    // 初始化轮播图
    public void initCarsuelView() {
        LinearLayout.LayoutParams cParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, SystemUtils.getScreenHeight() * 3 / 10);
        carouselImage.setLayoutParams(cParams);
        CartRecClassify carouselList = list.get(0);
        ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {
                // 实现点击事件
            }

            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                ImageLoaderHelper.getInstance().loadImage(imageURL, imageView);
            }
        };
        carouselImage.setImageResources(carouselList, mAdCycleViewListener);
        carouselImage.startImageCycle();
    }

    /**
     * 初始化下面的各个布局
     */
    public  void prepareLayout(){
        list.remove(0);

        mBaseListView.setAdapter(new ChannelAdapter(mBaseListView, list));
    }
    private class ChannelAdapter extends DefaultAdapter<CartRecClassify> {

        public ChannelAdapter(AbsListView listView, List<CartRecClassify> list) {
            super(listView, list);
        }
        @Override
        public boolean hasMore() {
            return false;
        }

        //告诉ListView每个位置是哪种样式的item
        @Override
        public int getItemViewTypeInner(int position) {

            return super.getItemViewTypeInner(position);
        }

        public BaseHolder getHolder() {

            return new CartRecHolder();
        }
//        @Override
//        public boolean hasMore() {
//            return false;
//        }
//        //告诉 有几种布局
//        @Override
//        public int getViewTypeCount() {
//            return super.getViewTypeCount();
//        }
//
//        //是告诉listView总共有几种样式的item
//        @Override
//        public int getViewTypeCount() {
//            return super.getViewTypeCount() + 1;
//        }
//
//        //告诉ListView每个位置是哪种样式的item
//        @Override
//        public int getItemViewTypeInner(int position) {
//            CategoryInfo groupInfo = getData().get(position);
//            if (groupInfo.isTitle()) {
//                return super.getItemViewTypeInner(position) + 1;
//            } else {
//                return super.getItemViewTypeInner(position);
//            }
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            mCurrentPosition = position;
//            return super.getView(position, convertView, parent);
//        }
//
//        public BaseHolder getHolder() {
//            CategoryInfo groupInfo = getData().get(mCurrentPosition);
//            if (groupInfo.isTitle()) {
//                return new CategoryTitleHolder();
//            } else {
//                return new CategoryHolder();
//            }
//        }
//
//
//        @Override
//        public int getItemViewTypeInner(int position) {
//                // 推荐位置布局
//
//                return super.getItemViewTypeInner(position) ;
//        }
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            return super.getView(position, convertView, parent);
//        }
//        @Override
//        protected BaseHolder getHolder() {
//                // 推荐位置布局
//                return  new ChannelHolder();
//            }
        }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }
}
