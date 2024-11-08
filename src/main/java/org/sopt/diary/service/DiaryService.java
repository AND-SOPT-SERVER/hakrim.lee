package org.sopt.diary.service;

import jakarta.transaction.Transactional;
import org.sopt.DiaryApplication;
import org.sopt.diary.domain.Diary;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;


@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @Transactional
    public void createDiary(Diary diary) {

        diary.setCreatedAt();
        diary.setUpdatedAt();

        try{
            diaryRepository.save(DiaryEntity.of(diary));
        } catch (Exception e){
            throw new DiaryApplication.DB.DbException("");
        }
    }

    @Transactional
    public void updateDiary(Long id, String content) {

        try {
            diaryRepository.updateContent(id, content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new DiaryApplication.DB.DataNotFound("사용자 정보를 읽어올 수 없습니다");
        }
    }

    public Diary getDetail(Long id) {

        return getDiary(id);
    }

    public List<Diary> getList() {

        final List<DiaryEntity> diaryEntityList = diaryRepository.findAll();
        return Diary.fromEntityList(diaryEntityList);
    }

    @Transactional
    public void deleteDiary(Long id) {

        try {
            //
            diaryRepository.deleteById(id);
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