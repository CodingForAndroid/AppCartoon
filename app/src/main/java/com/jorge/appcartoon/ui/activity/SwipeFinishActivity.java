package com.jorge.appcartoon.ui.activity;

import android.os.Bundle;

import com.jorge.appcartoon.R;
import com.jorge.appcartoon.widget.swipe.SwipeBackActivity;


/**
 * @author：Jorge on 2015/11/18 18:09
 */
public class SwipeFinishActivity extends SwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cart_ins_top);


//      My SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
//        mSwipeBackLayout.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);
////        mSwipeBackLayout.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);
//        setSwipeBackEnable(true);
    }
}
