package com.jorge.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "COMIC".
 */
public class Comic {

    private Long id;
    private int comic_id;
    private int chapter_id;
    private String title;
    private int picnum;
    private int filesize;
    private int chapter_order;

    public Comic() {
    }

    public Comic(Long id) {
        this.id = id;
    }

    public Comic(Long id, int comic_id, int chapter_id, String title, int picnum, int filesize, int chapter_order) {
        this.id = id;
        this.comic_id = comic_id;
        this.chapter_id = chapter_id;
        this.title = title;
        this.picnum = picnum;
        this.filesize = filesize;
        this.chapter_order = chapter_order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getComic_id() {
        return comic_id;
    }

    public void setComic_id(int comic_id) {
        this.comic_id = comic_id;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicnum() {
        return picnum;
    }

    public void setPicnum(int picnum) {
        this.picnum = picnum;
    }

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }

    public int getChapter_order() {
        return chapter_order;
    }

    public void setChapter_order(int chapter_order) {
        this.chapter_order = chapter_order;
    }

}