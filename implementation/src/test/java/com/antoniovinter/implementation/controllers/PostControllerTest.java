package com.antoniovinter.implementation.controllers;

import com.antoniovinter.api.dto.PostDto;
import com.antoniovinter.implementation.services.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private PostController postController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;


    @Test
    @DisplayName("When get all post, return an List with them")
    public void getAllPost() throws Exception {
        PostDto postDto = new PostDto("Going To Milano", "dsfsds");
        List<PostDto> posts = Collections.singletonList(postDto);
        doReturn(posts).when(postService).findAllPosts();

        this.mockMvc.perform(get("/post/"))
                .andExpect(jsonPath("$[0].title", Matchers.is("Going To Milano")));;
    }

    @Test
    public void getPostById() throws Exception {
        PostDto postDto = new PostDto("adf", "sdff");

        when(postService.findPostById(1)).thenReturn(postDto);
        mockMvc.perform(get("/post/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("adf"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("sdff"))
                .andExpect(status().isOk());

    }

    @Test
    public void savePost() throws Exception {
        PostDto post = new PostDto("title1", "description");

        when(postService.savePost(anyInt(), any(PostDto.class))).thenReturn(post);
        mockMvc.perform(post("/post?id=1")
                .content(new ObjectMapper().writeValueAsString(post))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description"));

    }
}
