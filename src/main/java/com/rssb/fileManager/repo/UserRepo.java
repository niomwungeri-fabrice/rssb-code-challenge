package com.rssb.fileManager.repo;

import com.rssb.fileManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

}
