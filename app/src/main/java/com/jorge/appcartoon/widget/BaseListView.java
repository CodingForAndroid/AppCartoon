package com.jorge.appcartoon.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.jorge.appcartoon.R;
import com.jorge.appcartoon.util.UIUtils;

// 设置过 selector的listview
public class BaseListView extends ListView {

	public BaseListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public BaseListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BaseListView(Context context) {
		super(context);
		init();
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 重写 onMeasure 动态计算 listview 高度
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	private void init() {
//		setDivider(UIUtils.getResources().getDrawable(R.drawable.nothing));
		setCacheColorHint(UIUtils.getColor(R.color.white));
//		setSelector(UIUtils.getResources().getDrawable(R.drawable.nothing));
		setHorizontalFadingEdgeEnabled(false);
		setDivider(null);//去除listview的下划线
	}

}
