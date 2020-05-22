package com.journal.blogstore.Controllers;

import com.journal.blogstore.Biz.BlogBizService;
import com.journal.blogstore.Controllers.Request.BlogRequest;
import com.journal.blogstore.Dtos.BlogDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping(path = "blog")
public class BlogController {


    @Autowired
    BlogBizService blogBizService;

    ModelMapper modelMapper = new ModelMapper();

    @RequestMapping(method = RequestMethod.POST, path = "/savenew")
    public ResponseEntity saveBlog(@RequestBody BlogRequest blogRequest){

        BlogDto blogDto = blogBizService.validateAndSaveBlog(modelMapper.map(blogRequest, BlogDto.class));

        return ResponseEntity.status(200).body("Successfully saved " + blogDto.getTitle());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/triggerindex")
    public ResponseEntity triggerBlog() throws IOException {
        blogBizService.triggerIndex();

        return ResponseEntity.status(200).body("success");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/searchAuthor")
    public ResponseEntity searchAuthor(@RequestParam String authorName) throws IOException {
        String response = blogBizService.searchByAuthor(authorName);
        return ResponseEntity.status(200).body(response);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/search")
    public ResponseEntity search(@RequestParam String query) throws IOException{
        String response = blogBizService.searchByTitleAndContent(query);
        return ResponseEntity.status(200).body(response);
    }
}
