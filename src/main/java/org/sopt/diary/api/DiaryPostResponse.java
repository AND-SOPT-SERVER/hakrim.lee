package org.sopt.diary.api;

public class DiaryPostResponse {
    private final String message;

    public DiaryPostResponse(String message){
        this.message = message;
    }

    //TODO 코톡
    static public DiaryPostResponse from(){
        return new DiaryPostResponse("일기 작성에 성공했습니다.");
    }
}
