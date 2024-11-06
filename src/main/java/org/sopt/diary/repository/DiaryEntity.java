package org.sopt.diary.repository;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//database를 class로 매핑시키는
//DB 정보를 java app에 끌어오는데 사용
@Entity
@Table(name = "diary")
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private String category;

    public DiaryEntity(
            final String title,
            final String content,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt,
            final String category
    ) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
    }

    public DiaryEntity() {}

    public long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCategory() {
        return category;
    }
}
