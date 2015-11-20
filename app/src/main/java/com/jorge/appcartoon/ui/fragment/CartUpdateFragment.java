package com.jorge.appcartoon.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.jorge.appcartoon.R;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.LoadingPage;
import com.konifar.fab_transformation.FabTransformation;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 漫画——更新
 *
 * @author：Jorge on 2015/11/10 17:21
 */
public class CartUpdateFragment extends BaseFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViewsAndEvents() {


    }

    @Override
    protected LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.SUCCEED;
    }

    @Override
    protected View createLoadedView() {

        return  UIUtils.inflate(R.layout.fragment_recommend);
    }


}
