package org.sopt.diary.api;


public class DiaryPatchRequest {
    private final String content;

    DiaryPatchRequest(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
