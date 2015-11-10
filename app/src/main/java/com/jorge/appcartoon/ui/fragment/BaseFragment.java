package com.jorge.appcartoon.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**  fragment 基类
 * @author：Jorge on 2015/11/10 13:59
 */
public abstract class BaseFragment extends Fragment {

public View  mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup parent= (ViewGroup) mRootView.getParent();
        if(parent!=null){
            parent.removeView(mRootView);
        }
        parent=null;
        return mRootView;
    }

    /**
     * 加载布局
     * @param layoutResID
     */
    public  void setContentView(int layoutResID) {
        mRootView = LayoutInflater.from(getActivity()).inflate(layoutResID,
                null);
        ButterKnife.bind(this,mRootView);
        initViews();
        addListener();
    }

    public  abstract  void initViews();

    public  abstract  void  addListener();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
