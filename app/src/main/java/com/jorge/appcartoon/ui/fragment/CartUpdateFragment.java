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

//    @Bind(R.id.fab)
//    FloatingActionButton fab;
//    @Bind(R.id.toolbar_footer)
//    Toolbar toolbarFooter;
//    @Bind(R.id.root)
//    RelativeLayout root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViewsAndEvents() {
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (fab.getVisibility() == View.VISIBLE) {
//                    FabTransformation.with(fab).transformTo(toolbarFooter);
//                }
//            }
//        });

    }

    @Override
    protected LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.SUCCEED;
    }

    @Override
    protected View createLoadedView() {
        View view = UIUtils.inflate(R.layout.fragment_recommend);
        ButterKnife.bind(this, view);
        return view;
    }

//    public  void firstClick(View view){
//        FabTransformation.with(fab).transformFrom(toolbarFooter);
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



}
