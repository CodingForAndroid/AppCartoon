package com.jorge.appcartoon.bean;

import java.util.List;

/**
     * ��Ʒ����
     */
    public class CartComment {
    //30
    public int comment_count;
    // ������� 2��
    public List<CommentInfo> latest_comment;

    public class CommentInfo {
        //388061
        public int comment_id;
        //2947149
        public int uid;
        //���}����
        public String nickname;
        //http://images.dmzj.com/user/58/33/58333e3d0ebb4219033f5aafa3620584.png
        public String avatar;
        // /c�ڶ�
        public String content;
        //1446782285
        public int createtime;

    }
}