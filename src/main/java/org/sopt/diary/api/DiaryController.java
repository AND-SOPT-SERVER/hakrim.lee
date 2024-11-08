package org.sopt.diary.api;

import org.sopt.diary.api.request.DiaryPatchRequest;
import org.sopt.diary.api.request.DiaryPostRequest;
import org.sopt.diary.api.response.DiaryDetailResponse;
import org.sopt.diary.api.response.DiaryListResponse;
import org.sopt.diary.domain.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.sopt.utils.validators.DiaryApiValidator.*;


@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    //TODO ResponseEntity의 역할은 무엇인가
    @PostMapping("/api/diary")
    ResponseEntity<String> post(@RequestBody DiaryPostRequest request) {

        try {
            isPostRequestValid(request);
            diaryService.createDiary(Diary.fromDiaryPostRequest(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/diary")
    ResponseEntity<DiaryListResponse> getDiaryList() {

        DiaryListResponse response = DiaryListResponse.of(diaryService.getList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/diary/{diaryId}")
    ResponseEntity<DiaryDetailResponse> getDiary(@PathVariable Long diaryId) {

        DiaryDetailResponse response = new DiaryDetailResponse(diaryService.getDetail(diaryId));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/diary/{diaryId}")
    ResponseEntity<Void> updateDiary(@PathVariable Long diaryId, @RequestBody DiaryPatchRequest request){
        try {
            isContentLengthValid(request.content());
            diaryService.updateDiary(diaryId, request.content());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    //TODO 없는 id를 삭제한 사실을 클라에 전달하지 못함
    @DeleteMapping("/api/diary/{diaryId}")
    ResponseEntity<Void> deleteDiary(@PathVariable Long diaryId) {
        try{
            diaryService.deleteDiary(diaryId);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}

