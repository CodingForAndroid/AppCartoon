package com.jorge.appcartoon.bean;

import java.util.List;

/**
 * ��������ҳ ����
 * @author��Jorge on 2015/11/16 12:25
 */
public class CartIns {
//    {
//        "id": 20706,
//            "islong": 2,
//            "title": "�ٺ٣�������",
//            "is_dmzj": 1,
//            "cover": "http://images.dmzj.com/img/webpic/12/1000911121444716924.jpg",
//            "description": "ڤ��˥�ܣ���ħ�ӳ���������֮ʱ������ڤ��ֻʣ����һ�ˡ���Ϊ���������ħ�������������Ψһ�İ취��ֻ�й�����·ǰ���˼䣬���������������Ѿ�ȴ����֮��һ���������������������ڵ��˽磬���˽�Ϊ��������һ�еı����ƺ������һ���޴����ı����",
//            "last_updatetime": 1446774393,
//            "copyright": 1,
//            "first_letter": "h",
//            "hot_num": 8145,
//            "uid": 100091112,
//            "status": [
//        {
//            "tag_id": 2309,
//                "tag_name": "������"
//        }
//        ],
//        "types": [
//        {
//            "tag_id": 3246,
//                "tag_name": "����"
//        },
//        {
//            "tag_id": 7568,
//                "tag_name": "��Ц"
//        }
//        ],
//        "authors": [
//        {
//            "tag_id": 8715,
//                "tag_name": "ǧ���ɳ"
//        }
//        ],
//        "subscribe_num": 912,
//            "chapters": [
//        {
//            "title": "����",
//                "data": [
//            {
//                "chapter_id": 44785,
//                    "chapter_title": "3.5��",
//                    "updatetime": 1446774393,
//                    "filesize": "97650",
//                    "chapter_order": 4
//            },
//            {
//                "chapter_id": 43711,
//                    "chapter_title": "3��",
//                    "updatetime": 1444460726,
//                    "filesize": "2564289",
//                    "chapter_order": 3
//            },
//            {
//                "chapter_id": 42741,
//                    "chapter_title": "2��",
//                    "updatetime": 1441442667,
//                    "filesize": "2786075",
//                    "chapter_order": 2
//            },
//            {
//                "chapter_id": 41584,
//                    "chapter_title": "1��",
//                    "updatetime": 1438756076,
//                    "filesize": "2530808",
//                    "chapter_order": 1
//            }
//            ]
//        }
//        ],
//        "author_notice": "ߣ�ֵ�PO��DF�L��_ǧ���Sɳ�����ָ��¶��ᷢPO��������ע��~\n����Ⱥ��ǧ���ɳ����Ⱥ 166151084������˵�Ƕ���Ⱥ����ӭ���ָ�������ˮ��",
//            "comic_notice": "ÿ��5�Ÿ��£����񣬾�����������΢����DF�L��_ǧ���Sɳ������Ⱥ��166151084����ӭ��·����",
//            "comment": {
//        "comment_count": 30,
//                "latest_comment": [
//        {
//            "comment_id": 388061,
//                "uid": 2947149,
//                "nickname": "���}����",
//                "avatar": "http://images.dmzj.com/user/58/33/58333e3d0ebb4219033f5aafa3620584.png",
//                "content": "/c�ڶ�",
//                "createtime": 1446782285
//        },
//        {
//            "comment_id": 387186,
//                "uid": 100462880,
//                "nickname": "��?С��",
//                "avatar": "http://images.dmzj.com/user/9c/9a/9c9a2817ffdeaa8ae5e90743a1bce537.png",
//                "content": "һ����һ���°�������",
//                "createtime": 1446272690
//        }
//        ]
//    }
//    }

    //20706
    public  int id;
     //2
     public  int islong;
    // �ٺ٣�������
    public  String   title;
    //is_dmzj
    public  int   is_dmzj;
    // http://images.dmzj.com/img/webpic/12/1000911121444716924.jpg
    public  String   cover;
    //ڤ��˥�ܣ���ħ�ӳ���������֮ʱ������ڤ��ֻʣ����һ�ˡ���Ϊ���������ħ�������������Ψһ�İ취��ֻ�й�����·ǰ���˼�...
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
    //��Ʒ�б�/1
    public List<CartStatus> status;
    //��Ʒ�������͡���ǩ/2
    public List<CartStatus> types;
    //����/1
    public List<CartStatus> authors;
    //912
    public int subscribe_num;
    //912
    public List<CartChapter> chapters ;
    //ߣ�ֵ�PO��DF�L��_ǧ���Sɳ�����ָ��¶��ᷢPO��������ע��~\n����Ⱥ��ǧ���ɳ����Ⱥ 166151084������˵�Ƕ���Ⱥ����ӭ���ָ�������ˮ��
    public String author_notice ;
    //ÿ��5�Ÿ��£����񣬾�����������΢����DF�L��_ǧ���Sɳ������Ⱥ��166151084����ӭ��·����
    public String  comic_notice;
    //��Ʒ����2
    public  CartComment comment;

    /**
     *�������״̬
     */
    private class CartStatus {

        //2309/3246/7568
        public int tag_id;

        // ������/����/��Ц
        public String tag_name;
    }

    /**
     * �����½�
     */
    private class CartChapter{

        //����
        public String  title;
        //2Chapters
        private List<Chapter> data;

        private class Chapter{
//        chapter_id	:	44785
//        chapter_title	:	3.5��
//        updatetime	:	1446774393
//        filesize	:	97650
//        chapter_order	:	4
            //44785
            public  int chapter_id;
            //3.5��
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
     * ��Ʒ����
     */
    private class CartComment{
        //30
        public int comment_count;
        // ������� 2��
        public  List<CartComment>latest_comment;

        class CommentInfo{
            //388061
            public  int comment_id;
            //2947149
            public int uid;
            //���}����
            public String nickname;
            //http://images.dmzj.com/user/58/33/58333e3d0ebb4219033f5aafa3620584.png
            public String avatar;
            // /c�ڶ�
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
