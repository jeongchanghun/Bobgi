package com.example.myapplication;

import android.text.TextUtils;
import android.widget.ImageView;

import java.util.ArrayList;

public class WatchManager {
    private final ArrayList<Dto> list = new ArrayList<>();

    private static class WatchManagerHolder {
        public static final WatchManager INSTANCE = new WatchManager();
    }

    public static WatchManager getInstance() {
        return WatchManager.WatchManagerHolder.INSTANCE;
    }

    public ArrayList<Dto> getList() {
        ArrayList<Dto> result = new ArrayList<>();
        for (Dto watchDto : list) {
            result.add(new Dto(watchDto.getMethod(), watchDto.getCategory(), watchDto.getDate(), watchDto.isChecked(), watchDto.getMember(), watchDto.getImage()));
        }
        return result;
    }

//    public boolean deleteById(String contId) {
////        return list.removeIf(watchDto -> TextUtils.equals(watchDto.getMember(), contId));
//    }

    public boolean deleteAll() {
        list.clear();
        return true;
    }

    public void initList() {
        list.clear();
        list.add(new Dto("배달", "한식", 20220302, true, 5));
    }
}
