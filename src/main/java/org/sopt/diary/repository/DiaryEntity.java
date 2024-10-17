package org.sopt.diary.repository;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//database를 class로 매핑시키는
//DB 정보를 java app에 끌어오는데 사용
@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String title;

    @Column
    public String content;

    @Column
    public LocalDateTime createdAt;

    @Column
    public String category;

    public DiaryEntity(final String title, final String content, final LocalDateTime createdAt, final String category) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.category = category;
    }

    public long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCategory() {
        return category;
    }
}
