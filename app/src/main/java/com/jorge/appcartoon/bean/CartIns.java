package com.jorge.appcartoon.bean;

import java.util.List;

/**
 * 漫画介绍页 数据
 * @author：Jorge on 2015/11/16 12:25
 */
public class CartIns {
//    {
//        "id": 20706,
//            "islong": 2,
//            "title": "嘿嘿，黑研社",
//            "is_dmzj": 1,
//            "cover": "http://images.dmzj.com/img/webpic/12/1000911121444716924.jpg",
//            "description": "冥府衰败，阎魔从沉眠中醒来之时，整个冥界只剩下他一人。身为天地正神，阎魔必须修正天道，唯一的办法，只有孤身上路前往人间，把生死簿上阳寿已尽却不死之人一个个“消除掉”，而现在的人界，凡人皆为永生，这一切的背后，似乎埋藏着一个巨大的阴谋……",
//            "last_updatetime": 1446774393,
//            "copyright": 1,
//            "first_letter": "h",
//            "hot_num": 8145,
//            "uid": 100091112,
//            "status": [
//        {
//            "tag_id": 2309,
//                "tag_name": "连载中"
//        }
//        ],
//        "types": [
//        {
//            "tag_id": 3246,
//                "tag_name": "耽美"
//        },
//        {
//            "tag_id": 7568,
//                "tag_name": "搞笑"
//        }
//        ],
//        "authors": [
//        {
//            "tag_id": 8715,
//                "tag_name": "千里黄沙"
//        }
//        ],
//        "subscribe_num": 912,
//            "chapters": [
//        {
//            "title": "连载",
//                "data": [
//            {
//                "chapter_id": 44785,
//                    "chapter_title": "3.5话",
//                    "updatetime": 1446774393,
//                    "filesize": "97650",
//                    "chapter_order": 4
//            },
//            {
//                "chapter_id": 43711,
//                    "chapter_title": "3话",
//                    "updatetime": 1444460726,
//                    "filesize": "2564289",
//                    "chapter_order": 3
//            },
//            {
//                "chapter_id": 42741,
//                    "chapter_title": "2话",
//                    "updatetime": 1441442667,
//                    "filesize": "2786075",
//                    "chapter_order": 2
//            },
//            {
//                "chapter_id": 41584,
//                    "chapter_title": "1话",
//                    "updatetime": 1438756076,
//                    "filesize": "2530808",
//                    "chapter_order": 1
//            }
//            ]
//        }
//        ],
//        "author_notice": "撸手的PO：DF繪師_千里黃沙，各种更新都会发PO，快来关注吧~\n读者群：千里黄沙腐腐群 166151084（与其说是读者群，欢迎各种腐物加入灌水）",
//            "comic_notice": "每月5号更新，管埋，尽管跳。渣浪微博：DF繪師_千里黃沙，读者群：166151084，欢迎各路腐物",
//            "comment": {
//        "comment_count": 30,
//                "latest_comment": [
//        {
//            "comment_id": 388061,
//                "uid": 2947149,
//                "nickname": "朝倉涼子",
//                "avatar": "http://images.dmzj.com/user/58/33/58333e3d0ebb4219033f5aafa3620584.png",
//                "content": "/c摆动",
//                "createtime": 1446782285
//        },
//        {
//            "comment_id": 387186,
//                "uid": 100462880,
//                "nickname": "陈?小喵",
//                "avatar": "http://images.dmzj.com/user/9c/9a/9c9a2817ffdeaa8ae5e90743a1bce537.png",
//                "content": "一个月一更新啊。。。",
//                "createtime": 1446272690
//        }
//        ]
//    }
//    }

    //20706
    public  int id;
     //2
     public  int islong;
    // 嘿嘿，黑研社
    public  String   title;
    //is_dmzj
    public  int   is_dmzj;
    // http://images.dmzj.com/img/webpic/12/1000911121444716924.jpg
    public  String   cover;
    //冥府衰败，阎魔从沉眠中醒来之时，整个冥界只剩下他一人。身为天地正神，阎魔必须修正天道，唯一的办法，只有孤身上路前往人间...
    public  String  description;
    //1446774393
    public  String  last_updatetime;
    //1
    public  String  copyright;
    //h
    public  String  first_letter;
    //8145
    public  String  hot_num;
    //100091112
    public  String  uid;
    //作品列表/1
    public List<CartStatus> status;
    //作品所属类型、标签/2
    public List<CartStatus> types;
    //作者/1
    public List<CartStatus> authors;
    //912
    public int subscribe_num;
    //912
    public List<CartChapter> chapters ;
    //撸手的PO：DF繪師_千里黃沙，各种更新都会发PO，快来关注吧~\n读者群：千里黄沙腐腐群 166151084（与其说是读者群，欢迎各种腐物加入灌水）
    public String author_notice ;
    //每月5号更新，管埋，尽管跳。渣浪微博：DF繪師_千里黃沙，读者群：166151084，欢迎各路腐物
    public String  comic_notice;
    //作品评论2
    public  CartComment comment;

    /**
     *漫画完结状态
     */
    private class CartStatus {

        //2309/3246/7568
        public int tag_id;

        // 连载中/耽美/搞笑
        public String tag_name;
    }

    /**
     * 漫画章节
     */
    private class CartChapter{

        //连载
        public String  title;
        //2Chapters
        private List<Chapter> data;

        private class Chapter{
//        chapter_id	:	44785
//        chapter_title	:	3.5话
//        updatetime	:	1446774393
//        filesize	:	97650
//        chapter_order	:	4
            //44785
            public  int chapter_id;
            //3.5话
            public  String chapter_title;
            //1446774393
            public  int updatetime;
            //97650
            public  int filesize;

            @Override
            public String toString() {
                return "Chapter{" +
                        "chapter_id=" + chapter_id +
                        ", chapter_title='" + chapter_title + '\'' +
                        ", updatetime=" + updatetime +
                        ", filesize=" + filesize +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "CartChapter{" +
                    "title='" + title + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    /**
     * 作品评论
     */
    private class CartComment{
        //30
        public int comment_count;
        // 最近评论 2；
        public  List<CartComment>latest_comment;

        class CommentInfo{
            //388061
            public  int comment_id;
            //2947149
            public int uid;
            //朝倉涼子
            public String nickname;
            //http://images.dmzj.com/user/58/33/58333e3d0ebb4219033f5aafa3620584.png
            public String avatar;
            // /c摆动
            public String content;
            //1446782285
            public int createtime ;

            @Override
            public String toString() {
                return "CommentInfo{" +
                        "comment_id=" + comment_id +
                        ", uid=" + uid +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", content='" + content + '\'' +
                        ", createtime=" + createtime +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "CartComment{" +
                    "comment_count=" + comment_count +
                    ", latest_comment=" + latest_comment +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CartIns{" +
                "id=" + id +
                ", islong=" + islong +
                ", title='" + title + '\'' +
                ", is_dmzj=" + is_dmzj +
                ", cover='" + cover + '\'' +
                ", description='" + description + '\'' +
                ", last_updatetime='" + last_updatetime + '\'' +
                ", copyright='" + copyright + '\'' +
                ", first_letter='" + first_letter + '\'' +
                ", hot_num='" + hot_num + '\'' +
                ", uid='" + uid + '\'' +
                ", status=" + status +
                ", types=" + types +
                ", authors=" + authors +
                ", subscribe_num=" + subscribe_num +
                ", chapters=" + chapters +
                ", author_notice='" + author_notice + '\'' +
                ", comic_notice='" + comic_notice + '\'' +
                ", comment=" + comment +
                '}';
    }
}
