package org.sopt.diary.api.request;

public record DiaryPostRequest(Long userId, String title, String content, String category) { }

