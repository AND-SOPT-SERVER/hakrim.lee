package org.sopt.diary.api.request;

public class DiaryPostRequest {
    private final String title;
    private final String content;
    private final String category;

    DiaryPostRequest(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

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

