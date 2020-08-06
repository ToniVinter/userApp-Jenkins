package com.antoniovinter.implementation.services;


import com.antoniovinter.api.dto.PostDto;
import com.antoniovinter.implementation.dao.repository.PostRepository;
import com.antoniovinter.implementation.dao.repository.UserRepository;
import com.antoniovinter.implementation.domain.Post;
import com.antoniovinter.implementation.domain.User;
import com.antoniovinter.implementation.exception.PostNotFound;
import com.antoniovinter.implementation.exception.UserNotFound;
import com.antoniovinter.implementation.services.converter.PostConverter;
import com.antoniovinter.implementation.services.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository repo;
    private UserRepository userRepo;
    private PostConverter postConverter;

    @Autowired
    public PostService(PostRepository repo, UserRepository userRepo, PostConverter postConverter) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.postConverter = postConverter;

    }

    public List<PostDto> findAllPosts() {
        List<Post> post = repo.findAll();
        List<PostDto> postDtos = repo.findAll().stream()
                .map(p -> postConverter.PostToDto(p))
                .collect(Collectors.toList());
        return postDtos;
    }

    public PostDto findPostById(int id) {
        Post post =  repo.findById(id)
                .orElseThrow(() -> new PostNotFound("post with id not found, id: " + id));

        return postConverter.PostToDto(post);
    }

    public List<PostDto> findAllPostsByUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFound("user not found with id: " + userId));
        List<PostDto> postDtos = repo.findAll().stream()
                .filter(p -> p.getUser().equals(user))
                .map(p -> postConverter.PostToDto(p))
                .collect(Collectors.toList());

        if (!postDtos.equals(Collections.emptyList())) {
            return postDtos;
        } else {
            throw new PostNotFound("posts not found with user ID: " + userId);
        }
    }

    public PostDto savePost(int userId, PostDto postDto) {
        Optional<User> userProfile = userRepo.findById(userId);
        Post post = new Post(postDto.getTitle(),postDto.getDescription());
        if (userProfile != null) {
            repo.save(post);
            return postDto;
        } else {
            throw new UserNotFound("User Is Not Found");
        }

    }

}
