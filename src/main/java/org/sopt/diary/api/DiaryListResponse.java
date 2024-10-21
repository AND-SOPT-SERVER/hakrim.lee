package org.sopt.diary.api;

import java.util.List;

public class DiaryListResponse {
    private List<DiaryResponse> diaryList;

    private DiaryListResponse(List<DiaryResponse> diaryList){
        this.diaryList = diaryList;
    }

    public static DiaryListResponse fromList(List<DiaryResponse> diaryList){
        return new DiaryListResponse((diaryList));
    }


    public List<DiaryResponse> getDiaryList(){
        return diaryList;
    }


}
