package com.jorge.appcartoon.http;

/**
 * 接口工具类
 * @author：Jorge on 2015/11/11 09:39
 */
public class ApiUtil {

    public static final String BASE_URL="http://v2.api.dmzj.com/";


    /**介绍页要替换的 comic_id*/
    public static  String REPLACE_COMIC_ID ="comic_id";
    /** 底层页要替换的 chapter_id*/
    public static  String REPLACE_CHAPTER_ID ="chapter_id";
    /**推荐*/
    public static final String CART_RECOMMEND_URL=BASE_URL+"recommend.json";
    /**漫画介绍页*/
    public static  String CART_INS_URL=BASE_URL+"comic/comic_id.json";
    /**底层页接口*/
    public static  String CART_CHAPTER_URL=BASE_URL+"chapter/comic_id/chapter_id.json";
    /**章节下载格式*/
    public static String CART_CHAPTER_DOWNLOAD_URL="http://imgzip.dmzj.com/first_letter/comic_id/chapter_id.zip";
}
