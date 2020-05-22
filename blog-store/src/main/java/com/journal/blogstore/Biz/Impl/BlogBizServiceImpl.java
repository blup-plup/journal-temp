package com.journal.blogstore.Biz.Impl;

import com.journal.blogstore.Biz.BlogBizService;
import com.journal.blogstore.Documents.BlogDocument;
import com.journal.blogstore.Dtos.BlogDto;
import com.journal.blogstore.MongoDbData.Blog;
import com.journal.blogstore.MongoRespositories.BlogRespository;
import com.journal.blogstore.Utils.ElasticUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class BlogBizServiceImpl implements BlogBizService {

    @Autowired
    BlogRespository blogRespository;

    @Autowired
    ElasticUtils elasticUtils;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public BlogDto validateAndSaveBlog(BlogDto blogDto) {

        Blog blog = modelMapper.map(blogDto,Blog.class);
        blog = blogRespository.save(blog);


        return modelMapper.map(blog,BlogDto.class);
    }


    @Override
    public void triggerIndex() throws IOException {
        List<Blog> blogList = blogRespository.findAll();

        for(Blog blog: blogList){
            BlogDocument blogDocument = new BlogDocument();
            blogDocument.setAuthorName(blog.getAuthorName());
            blogDocument.setContent(blog.getContent());
            blogDocument.setTitle(blog.getTitle());

            elasticUtils.indexBlogDoc(blogDocument);
        }
    }


    @Override
    public String searchByAuthor(String authorName) throws IOException {
        return elasticUtils.searchBlogsByAuthor(authorName);
    }

    @Override
    public String searchByTitleAndContent(String query) throws IOException {
        return elasticUtils.searchBlogsByTitleAndContent(query);
    }
}
