package com.jorge.appcartoon;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.jorge.appcartoon.widget.LoadingPage;
import com.konifar.fab_transformation.FabTransformation;

import butterknife.Bind;
import butterknife.OnClick;

public class TransformToPlayerActivity extends BaseActivity {
    @Override
    public void init() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void addListener() {

    }


//    @Bind(R.id.fab)
//    FloatingActionButton fab;
//    @Bind(R.id.container_player)
//    View containerPlayer;
//
//    public static void start(Context context, String title) {
//        Intent intent = new Intent(context, TransformToPlayerActivity.class);
//        intent.putExtra(EXTRA_TITLE, title);
//        context.startActivity(intent);
//    }
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.activity_transform_to_player;
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
//            FabTransformation.with(fab).transformTo(containerPlayer);
//        }
//    }
//
//    @OnClick(R.id.container_player)
//    void onClickSheet() {
//        if (fab.getVisibility() != View.VISIBLE) {
//            FabTransformation.with(fab).transformFrom(containerPlayer);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (fab.getVisibility() != View.VISIBLE) {
//            FabTransformation.with(fab).transformFrom(containerPlayer);
//            return;
//        }
//        super.onBackPressed();
//    }

}
