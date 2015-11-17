package com.jorge.appcartoon;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.LoadingPage;
import com.konifar.fab_transformation.FabTransformation;

import butterknife.Bind;
import butterknife.OnClick;

public class TransformToSheetActivity extends BaseActivity {
    @Override
    public void init() {

    }

    @Override
    public void initView() {

    }

//
//    @Bind(R.id.fab)
//    FloatingActionButton fab;
//    @Bind(R.id.sheet)
//    View sheet;
//    @Bind(R.id.overlay)
//    View overlay;
//
//    public static void start(Context context, String title) {
//        Intent intent = new Intent(context, TransformToSheetActivity.class);
//        intent.putExtra(EXTRA_TITLE, title);
//        context.startActivity(intent);
//    }
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.activity_transform_to_sheet;
//    }
//
//    @Override
//    protected void initView() {
//        //
//    }
//
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
//    }
//
//    @OnClick(R.id.row_5)
//    void onClickRow5() {
//        UIUtils.showToastSafe("Row5 clicked !");
//    }
//
//    @OnClick(R.id.overlay)
//    void onClickOverlay() {
//        if (fab.getVisibility() != View.VISIBLE) {
//            FabTransformation.with(fab).setOverlay(overlay).transformFrom(sheet);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (fab.getVisibility() != View.VISIBLE) {
//            FabTransformation.with(fab).setOverlay(overlay).transformFrom(sheet);
//            return;
//        }
//        super.onBackPressed();
//    }

}
