package com.antoniovinter.implementation.services.converter;


import com.antoniovinter.api.dto.PostDto;

import com.antoniovinter.implementation.domain.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    public PostDto PostToDto(Post post){
        ModelMapper modelMapper = new ModelMapper();
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }
}
