package com.rssb.fileManager.repo;

import com.rssb.fileManager.model.User;

import java.util.List;
import java.util.Map;

public interface UserRepoRedis {
    void saveToRedis(List<User> user);
    Map<Object, Object> findFromRedis();
}
