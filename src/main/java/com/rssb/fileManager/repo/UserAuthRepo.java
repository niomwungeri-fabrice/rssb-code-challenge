package com.rssb.fileManager.repo;

import com.rssb.fileManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAuthRepo extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
