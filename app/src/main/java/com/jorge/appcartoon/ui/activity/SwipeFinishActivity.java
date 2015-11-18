package com.jorge.appcartoon.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.jorge.appcartoon.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.ViewDragHelper;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * @author£ºJorge on 2015/11/18 18:09
 */
public class SwipeFinishActivity extends SwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cart_ins_top);


       SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);
    }
}
