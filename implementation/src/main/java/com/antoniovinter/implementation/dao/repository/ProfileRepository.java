package com.antoniovinter.implementation.dao.repository;

import com.antoniovinter.api.dto.ProfileDto;
import com.antoniovinter.implementation.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByUserName(String name);
}
