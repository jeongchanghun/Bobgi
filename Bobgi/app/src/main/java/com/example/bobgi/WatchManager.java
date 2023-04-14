package com.example.bobgi;

import android.text.TextUtils;

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
            result.add(new Dto(
                    watchDto.getProfile(),
                    watchDto.getTitle(),
                    watchDto.getCate(),
                    watchDto.getDate(),
                    watchDto.getMember(),
                    watchDto.isIscheked()));
        }
        return result;
    }

//    public boolean deleteById(String contId) {
//        return list.removeIf(watchDto -> TextUtils.equals(watchDto.getContId(), contId));
//    }

    public boolean deleteAll() {
        list.clear();
        return true;
    }

    public void initList() {
        list.clear();
    }
}
