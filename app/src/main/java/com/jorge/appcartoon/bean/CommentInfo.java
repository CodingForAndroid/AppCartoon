package com.jorge.appcartoon.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentInfo  implements Parcelable {
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

    protected CommentInfo(Parcel in) {
        comment_id = in.readInt();
        uid = in.readInt();
        nickname = in.readString();
        avatar = in.readString();
        content = in.readString();
        createtime = in.readInt();
    }

    public static final Creator<CommentInfo> CREATOR = new Creator<CommentInfo>() {
        @Override
        public CommentInfo createFromParcel(Parcel in) {
            return new CommentInfo(in);
        }

        @Override
        public CommentInfo[] newArray(int size) {
            return new CommentInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(comment_id);
        dest.writeInt(uid);
        dest.writeString(nickname);
        dest.writeString(avatar);
        dest.writeString(content);
        dest.writeInt(createtime);
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "avatar='" + avatar + '\'' +
                ", comment_id=" + comment_id +
                ", uid=" + uid +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}