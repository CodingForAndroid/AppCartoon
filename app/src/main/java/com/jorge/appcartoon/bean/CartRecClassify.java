package com.jorge.appcartoon.bean;

import java.util.List;

/**
 * 卡通推荐分类
 * @author：Jorge on 2015/11/10 18:38
 */
public class CartRecClassify {
    // 分类 id
    public int category_id;
    //火热专题、订阅更新
    public String  title;
    //
    public int sort;
    //作品列表
    public List<CartWork> data;

}
