package org.sopt.diary.api.request;

public class DiaryPostRequest {
    private final Long userId;
    private final String title;
    private final String content;
    private final String category;

    DiaryPostRequest(Long userId, String title, String content, String category) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Long getUserId(){ return userId; }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getCategory() {
        return category;
    }
}

