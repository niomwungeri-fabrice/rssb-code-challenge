package com.rssb.fileManager.repo;

import com.rssb.fileManager.model.User;
import com.rssb.fileManager.utils.UserErrors;

import java.util.List;
import java.util.Map;

public interface UserRepoRedis {
    void saveToRedis(UserErrors errors);
    Map findFromRedis();
}
