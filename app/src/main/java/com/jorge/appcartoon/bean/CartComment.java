package com.jorge.appcartoon.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 作品评论
 */
public class CartComment implements Parcelable{
    //30
    public int comment_count;
    // 最近评论 2；
    public List<CommentInfo> latest_comment;

    protected CartComment(Parcel in) {
        comment_count = in.readInt();
        latest_comment = in.createTypedArrayList(CommentInfo.CREATOR);
    }

    public static final Creator<CartComment> CREATOR = new Creator<CartComment>() {
        @Override
        public CartComment createFromParcel(Parcel in) {
            return new CartComment(in);
        }

        @Override
        public CartComment[] newArray(int size) {
            return new CartComment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(comment_count);
        dest.writeList(latest_comment);
    }

    @Override
    public String toString() {
        return "CartComment{" +
                "comment_count=" + comment_count +
                ", latest_comment=" + latest_comment +
                '}';
    }
}