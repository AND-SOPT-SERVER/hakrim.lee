package org.sopt.diary.api.response;

import org.sopt.diary.domain.Diary;

import java.util.List;

public record DiaryListResponse(List<DiaryResponse> diaryList) {

    public record DiaryResponse(String title, String content) { }

    public static DiaryListResponse of(List<Diary> diaryList) {

        List<DiaryResponse> diaryResponseList = diaryList.stream()
                .map(diary -> new DiaryResponse(diary.getTitle(), diary.getContent()))
                .toList();

        return new DiaryListResponse(diaryResponseList);
    }
}

