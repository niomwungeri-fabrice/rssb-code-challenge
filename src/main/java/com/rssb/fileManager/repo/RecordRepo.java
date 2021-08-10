package com.rssb.fileManager.repo;

import com.rssb.fileManager.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecordRepo extends JpaRepository<Record, UUID> {

}
