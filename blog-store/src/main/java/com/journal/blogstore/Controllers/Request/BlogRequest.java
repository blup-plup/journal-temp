package com.journal.blogstore.Controllers.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogRequest {

    String title;

    String content;

    String authorSugarId;

    String authorName;


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


}
