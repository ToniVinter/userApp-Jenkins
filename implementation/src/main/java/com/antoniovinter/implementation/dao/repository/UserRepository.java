package com.antoniovinter.implementation.dao.repository;

import com.antoniovinter.implementation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
