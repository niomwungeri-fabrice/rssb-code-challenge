package com.rssb.fileManager.repo;

import com.rssb.fileManager.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class RepoRedis {
    private static final String KEY = "records";

    private RedisTemplate redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RepoRedis(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    public void saveToRedis(List<Record> records) {
        hashOperations.put(KEY, "data", records);
    }


    public Map<Object, Object> findFromRedis() {
        return hashOperations.entries(KEY);
    }


    public void deleteFromRedis() {
        hashOperations.delete(KEY, "data");
    }
}
