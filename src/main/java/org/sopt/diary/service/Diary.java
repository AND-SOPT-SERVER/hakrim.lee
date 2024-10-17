package org.sopt.diary.service;

import java.time.LocalDateTime;

public class Diary {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final Category category;

    public Diary(Long id, String name, String content, LocalDateTime createdAt, String category) {
        this.id = id;
        this.title = name;
        this.content = content;
        this.createdAt = createdAt;
        this.category = toEnum(category);
    }

    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return toString(category);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private enum Category {
        DEFAULT,
        FOOD,
        WORKOUT,
        PROGRAMMING
    }

    private static Category toEnum(String category) {
        final Category enumCategory;

        if (category == null) {
            enumCategory = Category.DEFAULT;
        } else {
            enumCategory = switch (category) {
                case "FOOD" -> Category.FOOD;
                case "WORKOUT" -> Category.WORKOUT;
                case "PROGRAMMING" -> Category.PROGRAMMING;
                default -> Category.DEFAULT;
            };
        }
        return enumCategory;
    }

    private static String toString(Category category) {

        return switch (category) {
            case FOOD -> "FOOD";
            case WORKOUT -> "WORKOUT";
            case PROGRAMMING -> "PROGRAMMING";
            default -> "DEFAULT";
        };
    }
}
