package com.foxconn.lau.wanandroid.bean.main;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/4 下午 03:24
 */
/*{
        "apkLink": "",
        "author": "郭霖",
        "chapterId": 409,
        "chapterName": "郭霖",
        "collect_no": false,
        "courseId": 13,
        "desc": "",
        "envelopePic": "",
        "fresh": false,
        "id": 7648,
        "link": "https://mp.weixin.qq.com/s/-q-BqCG6PdJVpdEs6HntmQ",
        "niceDate": "2018-12-12",
        "origin": "",
        "projectLink": "",
        "publishTime": 1544544000000,
        "superChapterId": 408,
        "superChapterName": "公众号",
        "tags": [
        {
        "name": "公众号",
        "url": "/wxarticle/list/409/1"
        }
        ],
        "title": "Android混淆你该知道的事",
        "type": 0,
        "userId": -1,
        "visible": 1,
        "zan": 0
        }*/
public class Article implements Parcelable {
    private String apkLink;
    private String author;
    private String chapterId;
    private String chapterName;
    private boolean collect;
    private int courseId;
    private String desc;
    private String envelopePic;
    private boolean fresh;
    @Id(autoincrement = true)
    private Long id;
    private String link;
    private String niceDate;
    private String origin;
    private String projectLink;
    private long publishTime;
    private int superChapterId;
    private String superChapterName;
    private List<ArticleTag> tags;
    private String title;
    private int type;
    private int userId;
    private int visible;
    private int zan;

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }


    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public void setTags(List<ArticleTag> tags) {
        this.tags = tags;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public Article() {
        tags = new ArrayList<>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article(Parcel in) {
        apkLink = in.readString();
        author = in.readString();
        chapterId = in.readString();
        chapterName = in.readString();
        collect = in.readByte() != 0;
        courseId = in.readInt();
        desc = in.readString();
        envelopePic = in.readString();
        fresh = in.readByte() != 0;
        id = in.readLong();
        link = in.readString();
        niceDate = in.readString();
        origin = in.readString();
        projectLink = in.readString();
        publishTime = in.readLong();
        superChapterId = in.readInt();
        superChapterName = in.readString();
        tags = in.createTypedArrayList(ArticleTag.CREATOR);
        title = in.readString();
        type = in.readInt();
        userId = in.readInt();
        visible = in.readInt();
        zan = in.readInt();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public String getApkLink() {
        return apkLink;
    }

    public String getChapterId() {
        return chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getDesc() {
        return desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public List<ArticleTag> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

    public int getVisible() {
        return visible;
    }

    public int getZan() {
        return zan;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(apkLink);
        parcel.writeString(author);
        parcel.writeString(chapterId);
        parcel.writeString(chapterName);
        parcel.writeByte((byte) (collect ? 1 : 0));
        parcel.writeInt(courseId);
        parcel.writeString(desc);
        parcel.writeString(envelopePic);
        parcel.writeByte((byte) (fresh ? 1 : 0));
        parcel.writeLong(id);
        parcel.writeString(link);
        parcel.writeString(niceDate);
        parcel.writeString(origin);
        parcel.writeString(projectLink);
        parcel.writeLong(publishTime);
        parcel.writeInt(superChapterId);
        parcel.writeString(superChapterName);
        parcel.writeTypedList(tags);
        parcel.writeString(title);
        parcel.writeInt(type);
        parcel.writeInt(userId);
        parcel.writeInt(visible);
        parcel.writeInt(zan);
    }

    @Override
    public String toString() {
        return "Article{" +
                "apkLink='" + apkLink + '\'' +
                ", author='" + author + '\'' +
                ", chapterId='" + chapterId + '\'' +
                ", chapterName='" + chapterName + '\'' +
                ", collect=" + collect +
                ", courseId=" + courseId +
                ", desc='" + desc + '\'' +
                ", envelopePic='" + envelopePic + '\'' +
                ", fresh=" + fresh +
                ", id=" + id +
                ", link='" + link + '\'' +
                ", niceDate='" + niceDate + '\'' +
                ", origin='" + origin + '\'' +
                ", projectLink='" + projectLink + '\'' +
                ", publishTime=" + publishTime +
                ", superChapterId=" + superChapterId +
                ", superChapterName='" + superChapterName + '\'' +
                ", tags=" + tags +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", userId=" + userId +
                ", visible=" + visible +
                ", zan=" + zan +
                '}';
    }
}

