package com.example.bobgi;

import android.graphics.drawable.Drawable;

public class Dto {
    private int profile;
    private String title;
    private String cate;
    private String date;
    private String member;
    private boolean ischeked;

    public Dto(int profile, String title, String cate, String date, String member, boolean ischeked){
        this.profile = profile;
        this.title = title;
        this.cate = cate;
        this.date = date;
        this.member = member;
        this.ischeked = ischeked;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public boolean isIscheked() {
        return ischeked;
    }

    public void setIscheked(boolean ischeked) {
        this.ischeked = ischeked;
    }
}
