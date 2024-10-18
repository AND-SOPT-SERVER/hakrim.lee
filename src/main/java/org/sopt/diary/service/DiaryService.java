package org.sopt.diary.service;

import org.sopt.diary.DiaryApplication;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    //DiaryRepo의 빈을 주입, DI // 생성자에 주입했으므로
    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void createDiary(Diary diary) {

        diaryRepository.save(DiaryMapper.toEntity(diary));
    }

    //역할화할 것
    public void updateDiary(Long id, String content) {

        try {
            Diary diary = storedDiary(id);
            diary.setContent(content);
            diaryRepository.save(DiaryMapper.toEntity(diary));
        } catch (Exception e) {
            throw new DiaryApplication.DB.DataNotFound("사용자 정보를 읽어올 수 없습니다");
        }
    }

    public List<Diary> getList() {
        //repository 로부터 DiaryEntity를 가져옴
        final List<DiaryEntity> diaryEntityList = diaryRepository.findAll();
        final List<Diary> diaryList = new ArrayList<Diary>();
        for (DiaryEntity diaryEntity : diaryEntityList) {
            diaryList.add(Diary.from(
                    diaryEntity.getId(),
                    diaryEntity.getTitle(),
                    diaryEntity.getContent(),
                    diaryEntity.getCreatedAt(),
                    diaryEntity.getCategory()
            ));
        }
        return diaryList;
    }

    private Diary storedDiary(Long id) {
        Optional<DiaryEntity> diaryEntity = diaryRepository.findById(id);
        if (diaryEntity.isPresent()) {
            return(DiaryMapper.fromEntity(diaryEntity.get()));
        }else{
            throw new DiaryApplication.DB.DataNotFound("사용자 정보를 읽어올 수 없습니다.");
        }
    }
}
//Layer 단위 DTO가 있으면 좋다