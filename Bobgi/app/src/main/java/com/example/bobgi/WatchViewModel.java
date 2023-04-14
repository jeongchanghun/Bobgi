package com.example.bobgi;

import androidx.lifecycle.ViewModel;

import com.example.bobgi.Dto;
import com.example.bobgi.WatchManager;

import java.util.ArrayList;

public class WatchViewModel extends ViewModel {

    /**
     * 시청목록 전체 리스트 조회
     *
     * @return 전체 리스트
     */
    public ArrayList<Dto> getList() {
        return WatchManager.getInstance().getList();
    }

    /**
     * 시청목록 리스트에 데이터 추가 (데이터 없을때만 호출해야함)
     */
    public void addData() {
        WatchManager.getInstance().initList();
    }

    /**
     * 콘텐츠 삭제
     *
     * @param contId 콘텐츠 ID
     * @return 삭제 여부
     */
//    public boolean deleteById(String contId) {
//        return WatchManager.getInstance().deleteById(contId);
//    }

    /**
     * 시청목록 전부 삭제
     *
     * @return 삭제 여부
     */
    public boolean deleteAll() {
        return WatchManager.getInstance().deleteAll();

    }
}
