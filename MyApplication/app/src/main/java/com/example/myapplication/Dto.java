package com.example.myapplication;

import android.widget.ImageView;

public class Dto {
    private ImageView profile; // 프로필
    private String method; // 식사 방법
    private String category; // 카테고리
    private int date; // 날짜
    private boolean isChecked; //찜
    private int member; // 사람 수
    public Dto(String method, String category, int date, boolean isChecked, int member, ImageView image){
        this.method = method;
        this.category = category;
        this.date = date;
        this.isChecked = isChecked;
        this.member = member;
        this.profile = image;
    }

    public Dto(String method, String category, int date, boolean isChecked, int member) {
        this.method = method;
        this.category = category;
        this.date = date;
        this.isChecked = isChecked;
        this.member = member;
    }

    public String getMethod() {
        return method;
    }
    public String getCategory() {
        return category;
    }

    public int getDate() {
        return date;
    }
    public int getMember() {
        return member;
    }

    public ImageView getImage() {
        return profile;
    }

    public void setImage(ImageView profile) {
        this.profile = profile;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
    
    public void setMember(int member) {
        this.member = member;
    }
}
