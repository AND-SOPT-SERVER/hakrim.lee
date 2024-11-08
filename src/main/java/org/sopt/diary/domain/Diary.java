package org.sopt.diary.domain;

import org.sopt.diary.api.response.DiaryDetailResponse;
import org.sopt.diary.api.response.DiaryListResponse;
import org.sopt.diary.api.request.DiaryPostRequest;
import org.sopt.diary.repository.DiaryEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Diary {
    private final Long id;
    private final Long userId;
    private final String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private final String category;

    public Diary(Long id, Long userId, String name, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String category) {
        this.id = id;
        this.userId = userId;
        this.title = name;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
    }

    public static Diary fromDiaryPostRequest(DiaryPostRequest request) {
        return new Diary(
                null,
                request.userId(),
                request.title(),
                request.content(),
                null,
                null,
                request.category().toUpperCase()
        );
    }

    public static DiaryEntity toEntity(Diary diary) {
        return new DiaryEntity(

        );
    }

    public static Diary fromEntity(DiaryEntity entity) {
        return new Diary(
                entity.getId(),
                entity.getUserId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getCategory()
        );
    }

    public static List<Diary> fromEntityList(List<DiaryEntity> diaryEntityList) {
        List<Diary> list = new ArrayList<>();

        for (DiaryEntity diaryEntity : diaryEntityList) {
            list.add(Diary.fromEntity(diaryEntity));
        }
        return list;
    }


    public Long getUserId() { return userId; }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getCategory() {return category; }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }
    public void setUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }

}
