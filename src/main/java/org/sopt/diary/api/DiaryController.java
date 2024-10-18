package org.sopt.diary.api;

import org.sopt.diary.DiaryApplication;
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

    @PostMapping("/api/diary")
    DiaryPostResponse post(@RequestBody DiaryPostRequest postDiaryDto) {

        try{
            isContentLengthValid(postDiaryDto.getContent());
            diaryService.createDiary(DiaryMapper.toDiary(postDiaryDto));
            return DiaryPostResponse.success();
        }
        catch (Exception e){
            return DiaryPostResponse.fail();
        }
    }

    @GetMapping("/api/diary")
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

    //Exception 관련 코드 작성
    private void isContentLengthValid(String content) {
        if(content.length() > 30){
            throw new DiaryApplication.UI.InvalidInputException("일기 내용은 30자 이하만 가능합니다.");
        }
    }

}


