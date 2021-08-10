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
public class RecordRepoRedisImpl implements RecordRepoRedis {
    private static final String KEY = "users";


    private RedisTemplate redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RecordRepoRedisImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveToRedis(List<Record> users) {
        hashOperations.put(KEY, "data", users);
    }

    @Override
    public Map<Object, Object> findFromRedis() {
        return hashOperations.entries(KEY);
    }
}
