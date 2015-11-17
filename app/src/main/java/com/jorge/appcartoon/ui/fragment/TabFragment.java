package com.jorge.appcartoon.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.Button;
import com.jorge.appcartoon.R;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.LoadingPage;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 綁定Tab界面
 */
public class TabFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_cartoon)
    Button cartoonTab;
    @Bind(R.id.btn_news)
    Button newsTab ;
    @Bind(R.id.btn_novel)
    Button novelTab;
    @Bind(R.id.btn_mine)
    Button mineTab;
    @Bind(R.id.tabhost_task)
    FragmentTabHost mTabHost;
    private static final String HOMEPAGE_TAB1 = "tab1";
    private static final String HOMEPAGE_TAB2 = "tab2";
    private static final String HOMEPAGE_TAB3 = "tab3";
    private static final String HOMEPAGE_TAB4 = "tab4";
    private int currentTab = 0;
    private Drawable cartoonSelected, cartoonNormal, newsSelected, newsNromal, novelSelected,
            novelNormal, mineSelected  , mineNormal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_tab);
    }


    /**
     * 初始化Tab 圖標
     */
    private void initTabIcon() {
        cartoonSelected = getResources().getDrawable(R.mipmap.ic_tab_cart_pressed);
        cartoonNormal = getResources().getDrawable(R.mipmap.ic_tab_cart_normal);
        newsSelected = getResources().getDrawable(R.mipmap.ic_tab_news_pressed);
        newsNromal = getResources().getDrawable(R.mipmap.ic_tab_news_normal);
        novelSelected = getResources().getDrawable(R.mipmap.ic_tab_news_pressed);
        novelNormal = getResources().getDrawable(R.mipmap.ic_tab_news_normal);
        mineSelected = getResources().getDrawable(R.mipmap.ic_tab_mine_selected);
        mineNormal = getResources().getDrawable(R.mipmap.ic_tab_mine_normal);

        int right = cartoonSelected.getIntrinsicWidth();
        int bottom = cartoonNormal.getIntrinsicHeight();
        cartoonSelected.setBounds(0, 0, right, bottom);
        cartoonNormal.setBounds(0, 0, right, bottom);
        newsSelected.setBounds(0, 0, right, bottom);
        newsNromal.setBounds(0, 0, right, bottom);
        novelSelected.setBounds(0, 0, right, bottom);
        novelNormal.setBounds(0, 0, right, bottom);
        mineSelected.setBounds(0, 0, right, bottom);
        mineNormal.setBounds(0, 0, right, bottom);
    }


    /**
     * 當切換Tab的時候執行此方法
     * @param tabIndex
     */
    public void onTabChanged(int tabIndex){
        switch (tabIndex){
            case 0:
                cartoonTab.setTextColor(getResources().getColor(R.color.blue));
                newsTab.setTextColor(getResources().getColor(R.color.text_gray));
                novelTab.setTextColor(getResources().getColor(R.color.text_gray));
                mineTab.setTextColor(getResources().getColor(R.color.text_gray));

                cartoonTab.setCompoundDrawables(null, cartoonSelected, null, null);
                newsTab.setCompoundDrawables(null, newsNromal, null, null);
                novelTab.setCompoundDrawables(null, novelNormal, null, null);
                mineTab.setCompoundDrawables(null, mineNormal, null, null);
                break;
            case 1:
                cartoonTab.setTextColor(getResources().getColor(R.color.text_gray));
                newsTab.setTextColor(getResources().getColor(R.color.blue));
                novelTab.setTextColor(getResources().getColor(R.color.text_gray));
                mineTab.setTextColor(getResources().getColor(R.color.text_gray));

                cartoonTab.setCompoundDrawables(null, cartoonNormal, null, null);
                newsTab.setCompoundDrawables(null, newsSelected, null, null);
                novelTab.setCompoundDrawables(null, novelNormal, null, null);
                mineTab.setCompoundDrawables(null, mineNormal, null, null);
                break;
            case 2:
                cartoonTab.setTextColor(getResources().getColor(R.color.text_gray));
                newsTab.setTextColor(getResources().getColor(R.color.text_gray));
                novelTab.setTextColor(getResources().getColor(R.color.blue));
                mineTab.setTextColor(getResources().getColor(R.color.text_gray));

                cartoonTab.setCompoundDrawables(null, cartoonNormal, null, null);
                newsTab.setCompoundDrawables(null, newsNromal, null, null);
                novelTab.setCompoundDrawables(null, novelSelected, null, null);
                mineTab.setCompoundDrawables(null, mineNormal, null, null);
                break;
            case 3:
                cartoonTab.setTextColor(getResources().getColor(R.color.text_gray));
                newsTab.setTextColor(getResources().getColor(R.color.text_gray));
                novelTab.setTextColor(getResources().getColor(R.color.text_gray));
                mineTab.setTextColor(getResources().getColor(R.color.blue));

                cartoonTab.setCompoundDrawables(null, cartoonNormal, null, null);
                newsTab.setCompoundDrawables(null, newsNromal, null, null);
                novelTab.setCompoundDrawables(null, novelNormal, null, null);
                mineTab.setCompoundDrawables(null, mineSelected, null, null);
                break;
        }
        mTabHost.setCurrentTab(tabIndex);
    }

    @Override
    protected void initViewsAndEvents() {
        //初始化Tab 圖標
        initTabIcon();
        //給Tabhost 綁定內容
        mTabHost.setup(getActivity(), getChildFragmentManager(),
                R.id.tabcontent);
        mTabHost.getTabWidget().setVisibility(View.GONE);

        mTabHost.addTab(
                mTabHost.newTabSpec(HOMEPAGE_TAB1)
                        .setIndicator(HOMEPAGE_TAB1), CartoonFragment.class,
                null);
        mTabHost.addTab(
                mTabHost.newTabSpec(HOMEPAGE_TAB2)
                        .setIndicator(HOMEPAGE_TAB2), NewsFragment.class,
                null);
        mTabHost.addTab(
                mTabHost.newTabSpec(HOMEPAGE_TAB3)
                        .setIndicator(HOMEPAGE_TAB3), NovelFragment.class,
                null);
        mTabHost.addTab(
                mTabHost.newTabSpec(HOMEPAGE_TAB4)
                        .setIndicator(HOMEPAGE_TAB4), MineFragment.class, null);

        mTabHost.setCurrentTab(currentTab);
        //切換Tab
        onTabChanged(currentTab);

        cartoonTab.setOnClickListener(this);
        newsTab.setOnClickListener(this);
        novelTab.setOnClickListener(this);
        mineTab.setOnClickListener(this);
    }

    @Override
    protected LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.SUCCEED;
    }

    @Override
    protected View createLoadedView() {
        View view=     UIUtils.inflate(R.layout.fragment_tab);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cartoon:
                onTabChanged(0);
                break;
            case R.id.btn_news:
                onTabChanged(1);
                break;
            case R.id.btn_novel:
                onTabChanged(2);
                break;
            case R.id.btn_mine:
                onTabChanged(3);
                break;
        }

    }
}
