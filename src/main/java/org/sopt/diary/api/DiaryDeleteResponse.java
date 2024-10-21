package org.sopt.diary.api;

public class DiaryDeleteResponse {
    private int status;
    private String message;

    public DiaryDeleteResponse(int status, String message){
        this.status = status;
        this.message = message;
    }

    public static DiaryDeleteResponse success(){
        return new DiaryDeleteResponse(200, "일기 삭제에 성공했습니다.");
    }

    public static DiaryDeleteResponse fail(){
        return new DiaryDeleteResponse(404, "사용자 정보를 읽어올 수 없습니다.");
    }
}
