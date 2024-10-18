package org.sopt.diary.api;

public class DiaryPostResponse {
    private final String status;
    private final String message;

    public DiaryPostResponse(String status, String message){
        this.status = status;
        this.message = message;
    }

    //TODO 코톡에서 공유
    static public DiaryPostResponse success(){
        return new DiaryPostResponse("201", "일기 작성을 성공했습니다.");
    }
    static public DiaryPostResponse fail(){
        return new DiaryPostResponse("404", "일기 작성에 실패했습니다.");
    }
}
