package org.sopt.diary.api;

public class DiaryPatchResponse {
    private int status;
    private String message;

    public DiaryPatchResponse(int status, String message){
        this.status = status;
        this.message = message;
    }

    public static DiaryPatchResponse success(){
        return new DiaryPatchResponse(200, "일기 수정에 성공했습니다.");
    }

    public static DiaryPatchResponse fail(){
        return new DiaryPatchResponse(404, "사용자 정보를 읽어올 수 없습니다.");
    }

}
