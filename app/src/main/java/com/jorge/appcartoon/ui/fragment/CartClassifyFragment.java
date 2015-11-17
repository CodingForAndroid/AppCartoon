package com.jorge.appcartoon.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.jorge.appcartoon.R;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.LoadingPage;

/**
 *  漫画——分类
 * @author：Jorge on 2015/11/10 17:21
 */
public class CartClassifyFragment extends  BaseFragment {


    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.SUCCEED;
    }

    @Override
    protected View createLoadedView() {
//        setContentView(R.layout.fragment_recommend);
        return UIUtils.inflate(R.layout.fragment_recommend);
    }
}
