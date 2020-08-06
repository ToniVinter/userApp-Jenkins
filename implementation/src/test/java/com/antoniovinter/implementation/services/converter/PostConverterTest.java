package com.antoniovinter.implementation.services.converter;

import com.antoniovinter.api.dto.PostDto;
import com.antoniovinter.implementation.domain.Post;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class PostConverterTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PostConverter postConverter;


    @Test
    @DisplayName("When convert a post to dto, return PostDto")
    public void postToDto() {
        Post post = new Post("dsf","fds");

        PostDto postDto = postConverter.PostToDto(post);

        assertThat(post.getTitle()).isEqualTo(postDto.getTitle());
    }
}
