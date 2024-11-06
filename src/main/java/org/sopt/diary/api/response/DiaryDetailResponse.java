package org.sopt.diary.api;

import org.sopt.diary.service.Diary;

public class DiaryDetailResponse {
    private final long id;
    private final String title;
    private final String content;
    private final String createdAt;

    //TODO 코톡 - 어떤
    public DiaryDetailResponse(Diary diary) {

        this.id = diary.getId();
        this.title = diary.getTitle();
        this.content = diary.getTitle();
        this.createdAt = diary.getCreatedAt().toString();
    }
}
