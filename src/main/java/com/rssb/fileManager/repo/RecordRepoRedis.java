package com.rssb.fileManager.repo;

import com.rssb.fileManager.model.Record;

import java.util.List;
import java.util.Map;

public interface RecordRepoRedis {
    void saveToRedis(List<Record> records);
    Map<Object, Object> findFromRedis();
}
