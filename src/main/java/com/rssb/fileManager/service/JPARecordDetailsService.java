package com.rssb.fileManager.service;

import com.rssb.fileManager.model.Record;
import com.rssb.fileManager.repo.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPARecordDetailsService {

    @Autowired
    private RecordRepo recordRepo;

    public  List<Record> getAllRecords(){
        return recordRepo.findAll();
    }
}
