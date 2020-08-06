package com.antoniovinter.implementation.services;

import com.antoniovinter.api.dto.PostDto;
import com.antoniovinter.implementation.dao.repository.PostRepository;
import com.antoniovinter.implementation.dao.repository.UserRepository;
import com.antoniovinter.implementation.domain.Post;
import com.antoniovinter.implementation.domain.User;
import com.antoniovinter.implementation.exception.PostNotFound;
import com.antoniovinter.implementation.exception.UserNotFound;
import com.antoniovinter.implementation.services.converter.PostConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class PostServiceTest {

    Post expected;

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostConverter postConverter;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init(){
        expected = new Post("daf","sdf");
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("When user with id is found, return User")
    public void findPostById() {

        when(postRepository.findById(anyInt())).thenReturn(Optional.of(expected));
        when(postConverter.PostToDto(any())).thenReturn(new PostDto("daf", "sdf"));
        System.out.println(postService.findPostById(anyInt()));

        PostDto result = postService.findPostById(anyInt());

        assertThat(expected.getTitle()).isEqualTo(result.getTitle());
    }

    @Test(expected = PostNotFound.class)
    @DisplayName("When user with id is not found, throw UserNotFound")
    public void findPostByIdNull(){
        when(postRepository.findById(anyInt())).thenReturn(Optional.empty());

        postService.findPostById(anyInt());
    }

    @Test
    @DisplayName("When findAllPost is called, return List of UserDtos")
    public void findAllPost(){
        List<Post> posts = new ArrayList<>();
        posts.add(expected);
        when(postRepository.findAll()).thenReturn(posts);
        when(postConverter.PostToDto(any())).thenReturn(new PostDto(expected.getTitle(),expected.getDescription()));

        List<PostDto> postDtos = postService.findAllPosts();

        assertThat(posts.get(0).getDescription()).isEqualTo(postDtos.get(0).getDescription());
    }

    @Test
    @DisplayName("When save a post then return it")
    public void savePost(){
        User user = new User("sdf","fds",null, null);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(postRepository.save(any(Post.class))).thenReturn(expected);
        when(postConverter.PostToDto(any())).thenReturn(new PostDto(expected.getTitle(),expected.getDescription()));

        PostDto actual = postService.savePost(0,new PostDto(expected.getTitle(),expected.getDescription()));

        assertThat(expected.getDescription()).isEqualTo(actual.getDescription());
    }

    @Test(expected = UserNotFound.class)
    @DisplayName("When save a post with a null User throw UserNotFound")
    public void savePostNullCase(){

        when(userRepository.findById(anyInt())).thenReturn(null);
        when(postRepository.save(any(Post.class))).thenReturn(new Post());
        when(postConverter.PostToDto(any())).thenReturn(new PostDto("",""));

        postService.savePost(anyInt(),new PostDto("",""));

    }

    @Test
    @DisplayName("When find All posts by user id should return a List of posts")
    public void findAllPostsByUser() {
        List<Post> posts = new ArrayList<>();
        posts.add(expected);
        User user = new User("sfs", "fg", posts, null);
        expected.setUser(user);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(postRepository.findAll()).thenReturn(posts);
        when(postConverter.PostToDto(any())).thenReturn(new PostDto(expected.getTitle(), expected.getDescription()));

        List<PostDto> postDtos = postService.findAllPostsByUser(expected.getUser().getId());

        assertThat(posts.get(0).getTitle()).isEqualTo(postDtos.get(0).getTitle());
    }

    @Test(expected = PostNotFound.class)
    @DisplayName("When a user doesn't have posts, throw PostNotFound")
    public void findAllPostsByUserNullCase(){
        List<Post> posts = new ArrayList<>();
        User user = new User("sfs", "fg", null, null);
        expected.setUser(user);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(postRepository.findAll()).thenReturn(Collections.emptyList());
        when(postConverter.PostToDto(any())).thenReturn(null);

        postService.findAllPostsByUser(expected.getUser().getId());


    }





}
