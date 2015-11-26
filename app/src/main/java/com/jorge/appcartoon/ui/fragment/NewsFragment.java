package com.jorge.appcartoon.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorge.appcartoon.R;
import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.util.UIUtils;
import com.jorge.appcartoon.widget.BatteryView;
import com.jorge.appcartoon.widget.LoadingPage;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 新聞
 *
 * @author：Jorge on 2015/11/10 15:38
 */
public class NewsFragment extends BaseFragment {

    @Bind(R.id.battery_view)
    BatteryView mBatteryView;

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
        View view = UIUtils.inflate(R.layout.fragment_news);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        register();
    }

    private void register() {
       UIUtils.getContext(). registerReceiver(batteryChangedReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private void unregister() {
        UIUtils.getContext().   unregisterReceiver(batteryChangedReceiver);
    }
    // 接受广播
    private BroadcastReceiver batteryChangedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                int power = level * 100 / scale;
                LogUtils.e("电池电量：:" + power);
                mBatteryView.setPower(power);
            }
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregister();
        ButterKnife.unbind(this);
    }
}
