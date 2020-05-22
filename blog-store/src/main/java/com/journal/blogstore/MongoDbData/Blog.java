package com.journal.blogstore.MongoDbData;


import org.springframework.data.annotation.Id;

public class Blog {

    @Id
    String id;

    String title;

    String content;

    String authorSugarId;

    String authorName;

    String createdAt;

    String deletedAt;

    public Blog(String title, String content, String authorSugarId, String authorName, String createdAt, String deletedAt) {
        this.title = title;
        this.content = content;
        this.authorSugarId = authorSugarId;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorSugarId() {
        return authorSugarId;
    }

    public void setAuthorSugarId(String authorSugarId) {
        this.authorSugarId = authorSugarId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Blog() {
    }
}
