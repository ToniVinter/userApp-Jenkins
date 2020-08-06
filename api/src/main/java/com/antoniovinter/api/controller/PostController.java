package com.antoniovinter.api.controller;

import com.antoniovinter.api.dto.PostDto;
import com.antoniovinter.api.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("post")
public interface  PostController {

    @GetMapping()
    public List<PostDto> findAllPosts();

    @PostMapping()
    public PostDto savePost(@RequestParam int id, @RequestBody PostDto post);

    @GetMapping("/{id}")
    public PostDto findPostById(@PathVariable int id);
}
