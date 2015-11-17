package com.jorge.appcartoon.bean;

import java.util.List;

/**
 * 卡通推荐分类  "http://v2.api.dmzj.com/recommend.json"
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

    @Override
    public String toString() {
        return "CartRecClassify{" +
                "category_id=" + category_id +
                ", title='" + title + '\'' +
                ", sort=" + sort +
                ", data=" + data +
                '}';
    }
}
