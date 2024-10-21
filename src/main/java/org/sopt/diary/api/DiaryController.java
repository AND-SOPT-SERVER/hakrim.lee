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

    //TODO ResponseEntity의 역할은 무엇인가
    @PostMapping("/api/diary")
    ResponseEntity<DiaryPostResponse> post(@RequestBody DiaryPostRequest request) {

        try {
            isContentLengthValid(request.getContent());
            diaryService.createDiary(Diary.fromDiaryPostRequest(request));
            return ResponseEntity.ok(DiaryPostResponse.from());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/diary")
    ResponseEntity<DiaryListResponse> getList() {

        List<Diary> diaryList = diaryService.getList();
        List<DiaryResponse> diaryResponseList = DiaryResponse.fromList(diaryList);

        return ResponseEntity.ok(DiaryListResponse.fromList(diaryResponseList));
    }

    @GetMapping("/api/diary/{diaryId}")
    ResponseEntity<DiaryDetailResponse> get() {

        List<Diary> diaryList = diaryService.getDetail();
        return ResponseEntity.ok(DiaryDetailResponse.from());
    }


    @PatchMapping("/api/diary/{diaryId}")
    ResponseEntity<DiaryPatchResponse> updateDiary(@PathVariable Long diaryId, @RequestBody DiaryPatchRequest request){
        try {
            isContentLengthValid(request.getContent());
            diaryService.updateDiary(diaryId, request.getContent());
            return ResponseEntity.ok(DiaryPatchResponse.from());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/diary/{diaryId}")
    ResponseEntity<DiaryDeleteResponse> deleteDiary(@PathVariable Long diaryId) {
        try{
            diaryService.deleteDiary(diaryId);
            return ResponseEntity.ok(DiaryDeleteResponse.from());
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

