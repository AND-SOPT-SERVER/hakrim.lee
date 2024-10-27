package org.sopt.diary.api;

import org.sopt.diary.service.Diary;

import java.util.List;

public class DiaryListResponse {
    private List<Diary> diaryList;

    public DiaryListResponse(List<Diary> diaryList){
        this.diaryList = diaryList;
    }

    public static DiaryListResponse fromList(List<Diary> diaryList){
        return new DiaryListResponse((diaryList));
    }


    public List<Diary> getDiaryList(){
        return diaryList;
    }


}
