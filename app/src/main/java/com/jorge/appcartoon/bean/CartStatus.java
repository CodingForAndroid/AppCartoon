package com.jorge.appcartoon.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *漫画完结状态
 */
public class CartStatus implements Parcelable{

    //2309/3246/7568
    public int tag_id;

    // 连载中/耽美/搞笑
    public String tag_name;

    protected CartStatus(Parcel in) {
        tag_id = in.readInt();
        tag_name = in.readString();
    }

    public static final Creator<CartStatus> CREATOR = new Creator<CartStatus>() {
        @Override
        public CartStatus createFromParcel(Parcel in) {
            return new CartStatus(in);
        }

        @Override
        public CartStatus[] newArray(int size) {
            return new CartStatus[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(tag_id);
        dest.writeString(tag_name);
    }
}