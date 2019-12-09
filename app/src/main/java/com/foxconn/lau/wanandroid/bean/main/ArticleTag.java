package com.foxconn.lau.wanandroid.bean.main;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/4 下午 03:25
 */
public class ArticleTag implements Parcelable {
    private String name;
    private String url;

    public ArticleTag() {
    }

    protected ArticleTag(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<ArticleTag> CREATOR = new Creator<ArticleTag>() {
        @Override
        public ArticleTag createFromParcel(Parcel in) {
            return new ArticleTag(in);
        }

        @Override
        public ArticleTag[] newArray(int size) {
            return new ArticleTag[size];
        }
    };

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(url);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

