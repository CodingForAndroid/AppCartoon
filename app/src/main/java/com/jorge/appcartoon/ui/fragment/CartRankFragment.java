package com.jorge.appcartoon.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jorge.appcartoon.R;
import com.jorge.appcartoon.util.DrawableUtils;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.FlowLayout;
import com.jorge.appcartoon.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  漫画——排行
 * @author：Jorge on 2015/11/10 17:21
 */
public class CartRankFragment extends  BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initViewsAndEvents() {

    }
    private List<String> mDatas;
    @Override
    protected LoadingPage.LoadResult load() {
        mDatas=new ArrayList<String>();
        for(int i=0;i<10;i++){
            mDatas.add("第"+i+"话");
        }
        return LoadingPage.LoadResult.SUCCEED;
    }

    @Override
    protected View createLoadedView() {
        ScrollView mScrollView = new ScrollView(UIUtils.getContext());
        mScrollView.setFillViewport(true);//设置可以填充父窗体
        //初始化布局，该布局可以自动分配子View位置，保持每一行都能对齐
        FlowLayout   mLayout = new FlowLayout(UIUtils.getContext());
        mLayout.setBackgroundResource(R.drawable.grid_item_bg_normal);
        int layoutPadding = UIUtils.dip2px(13);
        mLayout.setPadding(layoutPadding, layoutPadding, layoutPadding, layoutPadding);
        mLayout.setHorizontalSpacing(layoutPadding);
        mLayout.setVerticalSpacing(layoutPadding);

        int textPaddingV = UIUtils.dip2px(4);
        int textPaddingH = UIUtils.dip2px(7);
        int backColor = 0xffcecece;
        int radius = UIUtils.dip2px(5);
        //代码动态创建一个图片
        GradientDrawable pressDrawable = DrawableUtils.createDrawable(backColor, backColor, radius);
        Random mRdm = new Random();
        for (int i = 0; i < mDatas.size(); i++) {
            TextView tv = new TextView(UIUtils.getContext());
            // 随机颜色的范围0x202020~0xefefef
            int red = 32 + mRdm.nextInt(208);
            int green = 32 + mRdm.nextInt(208);
            int blue = 32 + mRdm.nextInt(208);
            int color = 0xff000000 | (red << 16) | (green << 8) | blue;
            //创建背景图片选择器
            GradientDrawable normalDrawable = DrawableUtils.createDrawable(color, backColor, radius);
            StateListDrawable selector = DrawableUtils.createSelector(normalDrawable, pressDrawable);
            tv.setBackgroundDrawable(selector);

            final String text = mDatas.get(i);
            tv.setText(text);
            tv.setTextColor(Color.rgb(255, 255, 255));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(textPaddingH, textPaddingV, textPaddingH, textPaddingV);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIUtils.showToastSafe(text);
                }
            });
            mLayout.addView(tv);
        }
        mScrollView.addView(mLayout);
        return mScrollView;
    }
}
