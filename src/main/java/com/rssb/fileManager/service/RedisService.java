package com.rssb.fileManager.service;


import com.rssb.fileManager.repo.RepoRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisService {

    @Autowired
    private RepoRedis repoRedis;

    public Map<Object, Object> getRedisRecords() {
        try {
            return repoRedis.findFromRedis();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteAllRedisRecords() {
        try {
            repoRedis.deleteFromRedis();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
