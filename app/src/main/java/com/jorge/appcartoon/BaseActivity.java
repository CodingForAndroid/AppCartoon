package com.jorge.appcartoon;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.widget.swipe.SwipeBackActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public  abstract class BaseActivity extends SwipeBackActivity {

    /** 记录处于前台的Activity */
    private static BaseActivity mForegroundActivity = null;
    /** 记录所有活动的Activity */
    private static final List<BaseActivity> mActivities = new LinkedList<BaseActivity>();

    protected Handler handler = new MyHandler(this);
//	private static TextView mAccountId;

    private static class MyHandler extends Handler {
        private final WeakReference<BaseActivity> mWeakAct;

        public MyHandler(BaseActivity activity) {
            mWeakAct = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseActivity activity = mWeakAct.get();
            if (activity == null) {
                return;
            }
            activity.handleMsg(msg);
        }
    }

    /**
     * 消息处理
     *
     * @param msg
     */
    protected void handleMsg(Message msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        initView();
        overrideSlideEnterTransition();
        addListener();
    }

    @Override
    protected void onResume() {
        mForegroundActivity = this;
        super.onResume();
    }

    @Override
    protected void onPause() {
        mForegroundActivity = null;
        super.onPause();
    }

    public abstract void init();

    public abstract void initView();

    public abstract void addListener();

    /** 关闭所有Activity */
    public static void finishAll() {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
    }

    /** 关闭所有Activity，除了参数传递的Activity */
    public static void finishAll(BaseActivity except) {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy) {
            if (activity != except) activity.finish();
        }
    }


    /** 是否有启动的Activity */
    public static boolean hasActivity() {
        return mActivities.size() > 0;
    }

    /** 获取当前处于前台的activity */
    public static BaseActivity getForegroundActivity() {
        return mForegroundActivity;
    }

    /** 获取当前处于栈顶的activity，无论其是否处于前台 */
    public static BaseActivity getCurrentActivity() {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BaseActivity>(mActivities);
        }
        if (copy.size() > 0) {
            return copy.get(copy.size() - 1);
        }
        return null;
    }

    /** 推出应用 */
    public void exitApp() {
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    void overrideSlideEnterTransition() {
        overridePendingTransition(R.anim.activity_slide_start_enter, R.anim.activity_scale_start_exit);
    }

    void overrideSlideExitTransition() {
        overridePendingTransition(R.anim.activity_scale_finish_enter, R.anim.activity_slide_finish_exit);
    }

    @Override
    public void finish() {
        super.finish();
        overrideSlideExitTransition();
    }

}
