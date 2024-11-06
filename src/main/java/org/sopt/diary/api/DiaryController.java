package org.sopt.diary.api;

import org.sopt.DiaryApplication;
import org.sopt.diary.api.request.DiaryPatchRequest;
import org.sopt.diary.api.request.DiaryPostRequest;
import org.sopt.diary.api.response.*;
import org.sopt.diary.domain.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    //TODO ResponseEntity의 역할은 무엇인가
    @PostMapping("/api/diary")
    ResponseEntity<Void> post(@RequestBody DiaryPostRequest request) {

        try {
            isContentLengthValid(request.getContent());
            diaryService.createDiary(Diary.fromDiaryPostRequest(request));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/diary")
    ResponseEntity<DiaryListResponse> getDiaryList() {

        DiaryListResponse response = Diary.toDiaryListResponse(diaryService.getList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/diary/{diaryId}")
    ResponseEntity<DiaryDetailResponse> getDiary(@PathVariable Long diaryId) {

        DiaryDetailResponse response = Diary.toDiaryDetailResponse(diaryService.getDetail(diaryId));
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/api/diary/{diaryId}")
    ResponseEntity<Void> updateDiary(@PathVariable Long diaryId, @RequestBody DiaryPatchRequest request){
        try {
            isContentLengthValid(request.getContent());
            diaryService.updateDiary(diaryId, request.getContent());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/diary/{diaryId}")
    ResponseEntity<Void> deleteDiary(@PathVariable Long diaryId) {
        try{
            diaryService.deleteDiary(diaryId);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    //Exception 관련 코드 작성
    private void isContentLengthValid(String content) {
        if (content.length() > 30) {
            throw new DiaryApplication.UI.InvalidInputException("일기 내용은 30자 이하만 가능합니다.");
        }
    }
}

