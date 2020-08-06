package com.antoniovinter.implementation.controllers;

import com.antoniovinter.api.dto.PostDto;
import com.antoniovinter.implementation.services.PostService;
import com.antoniovinter.implementation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController implements com.antoniovinter.api.controller.PostController {

    @Autowired
    PostService postService;

    @Override
    public List<PostDto> findAllPosts() {
        return postService.findAllPosts();
    }

    @Override
    public PostDto savePost(int id, PostDto post) {
        return postService.savePost(id,post);
    }

    @Override
    public PostDto findPostById(int id) {
        return postService.findPostById(id);
    }
}
