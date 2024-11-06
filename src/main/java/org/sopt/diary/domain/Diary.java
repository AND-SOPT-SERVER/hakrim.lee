package org.sopt.diary.domain;

import org.sopt.diary.api.response.DiaryDetailResponse;
import org.sopt.diary.api.response.DiaryListResponse;
import org.sopt.diary.api.request.DiaryPostRequest;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.user.repository.UserEntity;

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
    private final Category category;

    public Diary(Long id, Long userId, String name, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String category) {
        this.id = id;
        this.userId = userId;
        this.title = name;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = toEnum(category);
    }

    public static DiaryEntity toEntity(Diary diary) {
        return new DiaryEntity(
                diary.getUserId(),
                diary.getTitle(),
                diary.getContent(),
                diary.getCreatedAt(),
                diary.getUpdatedAt(),
                diary.getCategory()
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

    public static Diary fromDiaryPostRequest(DiaryPostRequest request) {
        return new Diary(
                null,
                request.getUserId(),
                request.getTitle(),
                request.getContent(),
                null,
                null,
                request.getCategory()
        );
    }

    static public DiaryDetailResponse toDiaryDetailResponse(Diary diary){

        return new DiaryDetailResponse(diary);
    }


    public static DiaryListResponse toDiaryListResponse(List<Diary> diaryList){
        List<DiaryListResponse.DiaryResponse> diaryDetailResponses = diaryList.stream()
                .map(diary -> DiaryListResponse.DiaryResponse.of(diary.getTitle(), diary.getContent()))
                .toList();

        return DiaryListResponse.of(diaryDetailResponses);
    }


    public Long getId() {
        return id;
    }
    public Long getUserId() { return userId; }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return toString(category);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }
    public void setUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }

    private enum Category {
        DEFAULT,
        FOOD,
        WORKOUT,
        PROGRAMMING
    }

    private static Category toEnum(String category) {
        final Category enumCategory;

        if (category == null) {
            enumCategory = Category.DEFAULT;
        } else {
            enumCategory = switch (category) {
                case "FOOD" -> Category.FOOD;
                case "WORKOUT" -> Category.WORKOUT;
                case "PROGRAMMING" -> Category.PROGRAMMING;
                default -> Category.DEFAULT;
            };
        }
        return enumCategory;
    }

    private static String toString(Category category) {

        return switch (category) {
            case FOOD -> "FOOD";
            case WORKOUT -> "WORKOUT";
            case PROGRAMMING -> "PROGRAMMING";
            default -> "DEFAULT";
        };
    }
}
