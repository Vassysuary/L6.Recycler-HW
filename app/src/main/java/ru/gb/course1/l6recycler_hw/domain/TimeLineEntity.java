package ru.gb.course1.l6recycler_hw.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeLineEntity implements Parcelable {
    private String articleId;
    private String articleTitle;
    private String articleText;
    private String articleDateTime;

    public TimeLineEntity() {
    }

    public TimeLineEntity(String articleId, String articleTitle, String articleText, String articleDateTime) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleText = articleText;
        this.articleDateTime = articleDateTime;
    }

    protected TimeLineEntity(Parcel in) {
        articleId = in.readString();
        articleTitle = in.readString();
        articleText = in.readString();
        articleDateTime = in.readString();
    }

    public static final Creator<TimeLineEntity> CREATOR = new Creator<TimeLineEntity>() {
        @Override
        public TimeLineEntity createFromParcel(Parcel in) {
            return new TimeLineEntity(in);
        }

        @Override
        public TimeLineEntity[] newArray(int size) {
            return new TimeLineEntity[size];
        }
    };

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getArticleDateTime() {
        return articleDateTime;
    }

    public void setArticleDateTime(String articleDateTime) {
        this.articleDateTime = articleDateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(articleId);
        parcel.writeString(articleTitle);
        parcel.writeString(articleText);
        parcel.writeString(articleDateTime);
    }
}
