package com.jorge.appcartoon.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Chapter implements Parcelable {
//        chapter_id	:	44785
//        chapter_title	:	3.5话
//        updatetime	:	1446774393
//        filesize	:	97650
//        chapter_order	:	4
    //44785
    public int chapter_id;
    //3.5话
    public String chapter_title;
    //1446774393
    public int updatetime;
    //97650
    public int filesize;

    public String download_url;

    public String getDownloadUrl(){
        return download_url;
    }
    public void setDownload_url(String downloadUrl){
        this.download_url=downloadUrl;
    }
    protected Chapter(Parcel in) {
        chapter_id = in.readInt();
        chapter_title = in.readString();
        updatetime = in.readInt();
        filesize = in.readInt();
    }

    public static final Creator<Chapter> CREATOR = new Creator<Chapter>() {
        @Override
        public Chapter createFromParcel(Parcel in) {
            return new Chapter(in);
        }

        @Override
        public Chapter[] newArray(int size) {
            return new Chapter[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(chapter_id);
        dest.writeString(chapter_title);
        dest.writeInt(updatetime);
        dest.writeInt(filesize);
    }


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