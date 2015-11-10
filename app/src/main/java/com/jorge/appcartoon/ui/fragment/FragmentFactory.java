package com.jorge.appcartoon.ui.fragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactory {
	public static final int TAB_REC = 0;
	public static final int TAB_UPDATE = 1;
	public static final int TAB_CLASSIFY = 2;
	public static final int TAB_RANK= 3;
	public static final int TAB_SUBJECT= 4;
//	public static final int TAB_CATEGORY = 5;
//	public static final int TAB_TOP = 6;
	/** 记录所有的fragment，防止重复创建 */
	private static Map<Integer, BaseFragment> mFragmentMap = new HashMap<Integer, BaseFragment>();

	/** 采用工厂类进行创建Fragment，便于扩展，已经创建的Fragment不再创建 */
	public static BaseFragment createFragment(int index) {
		BaseFragment fragment = mFragmentMap.get(index);
		if (fragment == null) {
			switch (index) {
				case TAB_REC:
					fragment = new CartRecFragment();
					break;
				case TAB_UPDATE:
					fragment = new CartUpdateFragment();
					break;
				case TAB_CLASSIFY:
					fragment = new CartClassifyFragment();
					break;
				case TAB_RANK:
					fragment = new CartRankFragment();
					break;
				case TAB_SUBJECT:
					fragment = new CartSubjectFragment();
					break;
			}
			mFragmentMap.put(index, fragment);
		}
		return fragment;
	}
}
