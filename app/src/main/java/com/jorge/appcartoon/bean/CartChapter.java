package com.jorge.appcartoon.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

    /**
     * 章节漫画
     */
   public  class CartChapter implements Parcelable {

    /**连载*/
    public String title;
    /**2Chapters*/
    public ArrayList<Chapter> data;


        protected CartChapter(Parcel in) {
            title = in.readString();
            data = in.createTypedArrayList(Chapter.CREATOR);
        }

        public static final Creator<CartChapter> CREATOR = new Creator<CartChapter>() {
            @Override
            public CartChapter createFromParcel(Parcel in) {
                return new CartChapter(in);
            }

            @Override
            public CartChapter[] newArray(int size) {
                return new CartChapter[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeTypedList(data);
        }

        @Override
        public String toString() {
            return "CartChapter{" +
                    "data=" + data +
                    ", title='" + title + '\'' +
                    '}';
        }
    }