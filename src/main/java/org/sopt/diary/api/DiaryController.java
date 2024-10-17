package org.sopt.diary.api;

import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryMapper;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;
    //DiaryMapper는 주입안해도 되는가?
    public DiaryController(DiaryService diaryService){
        this.diaryService = diaryService;
    }

    //post라는 path에 맞는 요청을 핸들링 하는 api
    //역할은 request를 받고, response를 주는 역할
    @PostMapping("/api/diary")
    void post(@RequestBody PostDiaryDto postDiaryDto) {
        diaryService.createDiary(DiaryMapper.toDiary(postDiaryDto));
    }

    @GetMapping("/post")
    ResponseEntity<DiaryListResponse> get(){
        //서비스로부터 가져온 다이어리 리스트
        List<Diary> diaryList =  diaryService.getList();

        //클라와 협의한 interface로 변환
        List<DiaryResponse> diaryResponseList = new ArrayList<>();
        for(Diary diary: diaryList){
            diaryResponseList.add(new DiaryResponse((diary.getId()), diary.getTitle()));
        }

        return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
    }

}
