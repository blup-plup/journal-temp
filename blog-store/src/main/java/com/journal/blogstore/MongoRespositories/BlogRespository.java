package com.journal.blogstore.MongoRespositories;

import com.journal.blogstore.MongoDbData.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BlogRespository extends MongoRepository<Blog,String> {

    public List<Blog> findByAuthorSugarId(String authorSugarId);

}
