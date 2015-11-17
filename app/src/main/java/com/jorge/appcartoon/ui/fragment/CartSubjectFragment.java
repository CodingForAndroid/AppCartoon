package com.jorge.appcartoon.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.jorge.appcartoon.R;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.LoadingPage;

/**
 *  漫画——专题
 * @author：Jorge on 2015/11/10 17:21
 */
public class CartSubjectFragment extends  BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_recommend);
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
//        setContentView(R.layout.fragment_recommend);
        return UIUtils.inflate(R.layout.fragment_recommend);
    }
}
