package org.sopt.diary.service;

import org.sopt.diary.DiaryApplication;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;


@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    //DiaryRepo의 빈을 주입, DI // 생성자에 주입했으므로
    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void createDiary(Diary diary) {

        diaryRepository.save(Diary.toEntity(diary));
    }

    //역할화할 것
    public void updateDiary(Long id, String content) {

        try {
            Diary diary = getDiary(id);
            diary.setContent(content);
            diaryRepository.save(Diary.toEntity(diary));
        } catch (Exception e) {
            throw new DiaryApplication.DB.DataNotFound("사용자 정보를 읽어올 수 없습니다");
        }
    }

    public Diary getDetail(Long id) {

        return getDiary(id);
    }

    public List<Diary> getList() {
        //repository 로부터 DiaryEntity를 가져옴
        final List<DiaryEntity> diaryEntityList = diaryRepository.findAll();
        return Diary.fromEntityList(diaryEntityList);
    }

    public void deleteDiary(Long id) {

        try {
            Diary diary = getDiary(id);
            diaryRepository.delete(Diary.toEntity(diary));
        } catch (Exception e) {
            throw new DiaryApplication.DB.DataNotFound("사용자 정보를 읽어올 수 없습니다");
        }
    }

    private Diary getDiary(Long id) {
        Optional<DiaryEntity> diaryEntity = diaryRepository.findById(id);
        if (diaryEntity.isPresent()) {
            return (Diary.fromEntity(diaryEntity.get()));
        } else {
            throw new DiaryApplication.DB.DataNotFound("사용자 정보를 읽어올 수 없습니다.");
        }
    }
}