package com.jorge.appcartoon.bean;

public class Chapter {
        //        chapter_id	:	44785
//        chapter_title	:	3.5»°
//        updatetime	:	1446774393
//        filesize	:	97650
//        chapter_order	:	4
        //44785
        public int chapter_id;
        //3.5»°
        public String chapter_title;
        //1446774393
        public int updatetime;
        //97650
        public int filesize;

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