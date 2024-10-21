package org.sopt.diary.api;

import org.sopt.diary.DiaryApplication;
import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/api/diary")
    DiaryPostResponse post(@RequestBody DiaryPostRequest request) {

        try {
            isContentLengthValid(request.getContent());
            diaryService.createDiary(Diary.fromDiaryPostRequest(request));
            return DiaryPostResponse.success();
        } catch (Exception e) {
            return DiaryPostResponse.fail();
        }
    }

    @GetMapping("/api/diary")
    ResponseEntity<DiaryListResponse> get() {

        List<Diary> diaryList = diaryService.getList();
        List<DiaryResponse> diaryResponseList = DiaryResponse.fromList(diaryList);

        return ResponseEntity.ok(DiaryListResponse.fromList(diaryResponseList));
    }

//    @GetMapping("/api/diary/{diaryId}")
//    ResponseEntity<DiaryListResponse> get() {
//
//        List<Diary> diaryList = diaryService.getList();
//        List<DiaryResponse> diaryResponseList = DiaryResponse.fromList(diaryList);
//
//        return ResponseEntity.ok(DiaryListResponse.fromList(diaryResponseList));
//    }


    @PatchMapping("/api/diary/{diaryId}")
    DiaryPatchResponse updateDiary(@PathVariable Long diaryId, @RequestBody DiaryPatchRequest request){
        try {
            isContentLengthValid(request.getContent());
            diaryService.updateDiary(diaryId, request.getContent());
            return DiaryPatchResponse.success();
        } catch (Exception e) {
            return DiaryPatchResponse.fail();
        }
    }

    @DeleteMapping("/api/diary/{diaryId}")
    DiaryDeleteResponse deleteDiary(@PathVariable Long diaryId) {
        try{
            diaryService.deleteDiary(diaryId);
            return DiaryDeleteResponse.success();
        } catch (Exception e){
            return DiaryDeleteResponse.fail();
        }
    }
    //Exception 관련 코드 작성
    private void isContentLengthValid(String content) {
        if (content.length() > 30) {
            throw new DiaryApplication.UI.InvalidInputException("일기 내용은 30자 이하만 가능합니다.");
        }
    }
}

