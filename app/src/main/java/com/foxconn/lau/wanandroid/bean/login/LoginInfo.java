package com.foxconn.lau.wanandroid.bean.login;

import java.util.Arrays;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/2 上午 10:07
 */

public class LoginInfo {

    private boolean admin;
    private String[] chapterTops;
    private int[] collectIds;
    private String email;
    private String icon;
    private long id;
    private String publicName;
    private String password;
    private long type;
    private String username;
    private String nickname;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String[] getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(String[] chapterTops) {
        this.chapterTops = chapterTops;
    }

    public int[] getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(int[] collectIds) {
        this.collectIds = collectIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "admin=" + admin +
                ", chapterTops=" + Arrays.toString(chapterTops) +
                ", collectIds=" + Arrays.toString(collectIds) +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", publicName='" + publicName + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
