package com.jorge.appcartoon.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *  章节详情、包含当前话的内容
 * @author：Jorge on 2015/11/20 15:20
 */
public class ChapterDetail {

/*     {
         "chapter_id": 43795,
             "comic_id": 17149,
             "title": "第38话",
             "chapter_order": 420,
             "direction": 0,
             "page_url": [
         "http://imgsmall.dmzj.com/h/17149/43795/0.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/1.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/2.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/3.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/4.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/5.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/6.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/7.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/8.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/9.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/10.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/11.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/12.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/13.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/14.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/15.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/16.jpg",
                 "http://imgsmall.dmzj.com/h/17149/43795/17.jpg"
         ],
         "picnum": 18,
             "comment_count": 272
     }*/
    //
    public int chapter_id;
    //17149
    public int comic_id;
    //第38话
    public String title;
    //420
    public int chapter_order;
    //0
    public int direction;
    //18
    public int picnum;
    //comment_count
    public int comment_count;
    //网址Url链接
    public List<String> page_url;

}
