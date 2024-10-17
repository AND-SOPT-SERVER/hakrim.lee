package org.sopt.diary.service;

import org.sopt.diary.api.PostDiaryDto;
import org.sopt.diary.repository.DiaryEntity;

public class DiaryMapper {
    public static Diary toDiary(PostDiaryDto dto) {
        return new Diary(
                null,
                dto.getTitle(),
                dto.getContent(),
                null,
                dto.getCategory()
        );
    }

    public static DiaryEntity toDiaryEntity(Diary diary){
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

