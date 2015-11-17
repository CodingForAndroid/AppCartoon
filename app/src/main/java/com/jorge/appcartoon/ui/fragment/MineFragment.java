package com.jorge.appcartoon.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jorge.appcartoon.R;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.LoadingPage;
import com.jorge.appcartoon.widget.PagerTab;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的
 *
 * @author：Jorge on 2015/11/10 15:38
 */
public class MineFragment extends BaseFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_cartoon);
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
        return UIUtils.inflate(R.layout.fragment_recommend);
    }
}
