package org.sopt.diary.api.response;

import java.util.List;

public record DiaryListResponse(List<DiaryResponse> diaryList) {
    public record DiaryResponse(String title, String content) {
        public static DiaryResponse of(String title, String content){
            return new DiaryResponse(title, content);
        }
    }

    public static DiaryListResponse of(List<DiaryResponse> diaryList){
        return new DiaryListResponse(diaryList);
    }
}
