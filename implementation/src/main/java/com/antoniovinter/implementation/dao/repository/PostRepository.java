package com.antoniovinter.implementation.dao.repository;

import com.antoniovinter.implementation.domain.Post;
import com.antoniovinter.implementation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PostRepository extends JpaRepository<Post, Integer> {

    Post findByUser(User user);
}
