package org.sopt.diary.service;

import org.sopt.diary.api.DiaryPostRequest;
import org.sopt.diary.repository.DiaryEntity;

public class DiaryMapper {
    public static Diary toDiary(DiaryPostRequest dto) {
        return new Diary(
                null,
                dto.getTitle(),
                dto.getContent(),
                null,
                dto.getCategory()
        );
    }

    public static Diary fromEntity(DiaryEntity entity) {
        return Diary.from(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCategory()
        );
    }

    public static DiaryEntity toEntity(Diary diary){
        return new DiaryEntity(
                diary.getTitle(),
                diary.getContent(),
                diary.getCreatedAt(),
                diary.getCategory()
        );
    }

//    public static DiaryDao toDiaryEntity(Diary diary){
//
//    }

    // Convert Domain object to DTO
//    public static DiaryDTO toDiaryDTO(Diary diary) {
//        DiaryDTO dto = new DiaryDTO();
//        dto.setTitle(diary.getTitle());
//        dto.setContent(diary.getContent());
//        return dto;
//    }

}

