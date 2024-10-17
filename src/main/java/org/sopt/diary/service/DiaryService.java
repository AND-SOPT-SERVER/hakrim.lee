package org.sopt.diary.service;

import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;


@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    //DiaryRepo의 빈을 주입, DI // 생성자에 주입했으므로
    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void createDiary(Diary diary) {

        diaryRepository.save(DiaryMapper.toDiaryEntity(diary));
    }

    public List<Diary> getList() {
        //repository 로부터 DiaryEntity를 가져옴
        final List<DiaryEntity> diaryEntityList = diaryRepository.findAll();
        final List<Diary> diaryList = new ArrayList<Diary>();
        for (DiaryEntity diaryEntity : diaryEntityList) {
            //TODO 여기에 정윤님 의견 적용
            diaryList.add(new Diary(
                    diaryEntity.getId(),
                    diaryEntity.getTitle(),
                    diaryEntity.getContent(),
                    diaryEntity.getCreatedAt(),
                    diaryEntity.getCategory()
            ));
        }
        return diaryList;
    }
}
//Layer 단위 DTO가 있으면 좋다