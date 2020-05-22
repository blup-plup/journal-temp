package com.journal.blogstore.Biz;

import com.journal.blogstore.Dtos.BlogDto;

import java.io.IOException;

public interface BlogBizService {

    public BlogDto validateAndSaveBlog(BlogDto blogDto);

    public void triggerIndex() throws IOException;

    public String searchByAuthor(String authorName) throws IOException;

    public String searchByTitleAndContent(String query) throws IOException;

}
