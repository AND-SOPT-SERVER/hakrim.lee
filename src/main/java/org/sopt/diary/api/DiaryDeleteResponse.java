package org.sopt.diary.api;

public class DiaryDeleteResponse {
    private String message;

    public DiaryDeleteResponse(String message){
        this.message = message;
    }

    public static DiaryDeleteResponse from(){
        return new DiaryDeleteResponse("일기 삭제에 성공했습니다.");
    }
    
}
