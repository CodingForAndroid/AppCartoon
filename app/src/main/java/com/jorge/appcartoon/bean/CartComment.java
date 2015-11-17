package com.jorge.appcartoon.bean;

import java.util.List;

/**
     * 作品评论
     */
    public class CartComment {
    //30
    public int comment_count;
    // 最近评论 2；
    public List<CommentInfo> latest_comment;

    public class CommentInfo {
        //388061
        public int comment_id;
        //2947149
        public int uid;
        //朝倉涼子
        public String nickname;
        //http://images.dmzj.com/user/58/33/58333e3d0ebb4219033f5aafa3620584.png
        public String avatar;
        // /c摆动
        public String content;
        //1446782285
        public int createtime;

    }
}