package com.jorge.appcartoon;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jorge.appcartoon.ui.activity.BaseFragmentActivity;
import com.jorge.appcartoon.ui.fragment.TabFragment;
import com.jorge.appcartoon.util.UIUtils;
import com.konifar.fab_transformation.FabTransformation;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 主Activity
 */
public class MainActivity extends BaseFragmentActivity {

//    @Bind(R.id.content_frame)
//    FrameLayout contentFrame;
//    @Bind(R.id.overlay)
//    View overlay;
//    @Bind(R.id.fab)
//    FloatingActionButton fab;
//    @Bind(R.id.img_icon)
//    ImageView imgIcon;
//    @Bind(R.id.txt_title)
//    TextView txtTitle;
//    @Bind(R.id.row_5)
//    RelativeLayout row5;
//    @Bind(R.id.sheet)
//    CardView sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }


    /**
     * 添加tab fragment
     */
    private void initViews() {
        TabFragment tabFragment = new TabFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.content_frame, tabFragment, tabFragment.getClass().getName()).commit();

    }

//
//    @Override
//    public void onBackPressed() {
//        if (fab.getVisibility() != View.VISIBLE) {
//            FabTransformation.with(fab).setOverlay(overlay).transformFrom(sheet);
//            return;
//        }
//        super.onBackPressed();
//    }
//    @OnClick(R.id.fab)
//    void onClickFab() {
//        if (fab.getVisibility() == View.VISIBLE) {
//            FabTransformation.with(fab).setOverlay(overlay).transformTo(sheet);
//        }
//    }
//
//    @OnClick(R.id.row_1)
//    void onClickRow1() {
//        UIUtils.showToastSafe("Row1 clicked !");
//    }
//
//    @OnClick(R.id.row_2)
//    void onClickRow2() {
//        UIUtils.showToastSafe("Row 2 clicked !");
//    }
//
//    @OnClick(R.id.row_3)
//    void onClickRow3() {
//        UIUtils.showToastSafe("Row3 clicked !");
//    }
//
//    @OnClick(R.id.row_4)
//    void onClickRow4() {
//        UIUtils.showToastSafe("Row4clicked !");
//        if (fab.getVisibility() != View.VISIBLE) {
//            FabTransformation.with(fab).setOverlay(overlay).transformFrom(sheet);
//        }
//    }
//
//    @OnClick(R.id.row_5)
//    void onClickRow5() {
//        UIUtils.showToastSafe("Row5 clicked !");
//        if (fab.getVisibility() != View.VISIBLE) {
//            FabTransformation.with(fab).setOverlay(overlay).transformFrom(sheet);
//        }
//    }
//
//    @OnClick(R.id.overlay)
//    void onClickOverlay() {
//        if (fab.getVisibility() != View.VISIBLE) {
//            FabTransformation.with(fab).setOverlay(overlay).transformFrom(sheet);
//        }
//    }

}
