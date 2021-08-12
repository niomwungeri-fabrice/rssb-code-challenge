package com.rssb.fileManager.service;

import com.rssb.fileManager.model.Record;
import com.rssb.fileManager.repo.RecordRepo;
import com.rssb.fileManager.repo.RepoRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JPARecordDetailsService {

    @Autowired
    RepoRedis repoRedis;

    @Autowired
    RecordRepo recordRepo;


    public void deleteAllFromDB() {
        try {
            recordRepo.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Record> getAllRecords() {
        try {
            return recordRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void SaveToDB() {
        try {
            List<Record> records = (List<Record>) repoRedis.findFromRedis().get("data");
            if (records == null)
                throw new RuntimeException("No Records found in the memory");
            recordRepo.saveAll(records);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void saveErrorFreeToDB() {
        try {
            List<Record> records = (List<Record>) repoRedis.findFromRedis().get("data");
            if (records == null)
                throw new RuntimeException("No records found in the memory");
            List<Record> errorFreeRecords = new ArrayList<>();
            for (Record record : records) {
                if (record.getErrors().isEmpty()) {
                    errorFreeRecords.add(record);
                }
            }
            recordRepo.saveAll(errorFreeRecords);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
