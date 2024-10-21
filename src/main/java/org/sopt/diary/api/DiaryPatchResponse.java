package org.sopt.diary.api;

public class DiaryPatchResponse {
    private String message;

    public DiaryPatchResponse(String message){
        this.message = message;
    }

    public static DiaryPatchResponse from(){
        return new DiaryPatchResponse("일기 수정에 성공했습니다.");
    }
}
