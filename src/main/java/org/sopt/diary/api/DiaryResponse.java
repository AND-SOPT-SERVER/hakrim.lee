package org.sopt.diary.api;


import org.sopt.diary.service.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryResponse {
    private final long id;
    private final String title;

    private DiaryResponse(long id, String title){
        this.id = id;
        this.title = title;
    }

    public static List<DiaryResponse> fromList(List<Diary> diaryList) {
        List<DiaryResponse> list = new ArrayList<>();

        for (Diary diary : diaryList) {
            list.add(new DiaryResponse(diary.getId(), diary.getTitle()));
        }
        return list;
    }

    public long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
}
