package com.rssb.fileManager.controller;

import com.rssb.fileManager.exception.HttpResponseHandler;
import com.rssb.fileManager.model.Record;
import com.rssb.fileManager.service.JPARecordDetailsService;
import com.rssb.fileManager.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class RecordController {

    @Autowired
    private JPARecordDetailsService jpaRecordDetailsService;


    @GetMapping("/db/records")
    public ResponseEntity<?> getAllRecords(@RequestParam(value="pageNumber" , required = false)Integer pageNumber, @RequestParam(value="pageSize" , required = false)Integer pageSize) {
       try {
           if(pageNumber == null) pageNumber = 1;
           if(pageSize == null) pageSize = 20;
           List<Record> records = Pagination.getPage(jpaRecordDetailsService.getAllRecords(), pageNumber, pageSize);
           return new ResponseEntity<>(records, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", e.getMessage()),
                   HttpStatus.BAD_REQUEST);
       }
    }


    @DeleteMapping("/db/records")
    public ResponseEntity<?> deleteAll() {
        try {
            jpaRecordDetailsService.deleteAllFromDB();
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("message","Records Deleted from Database"),
                    HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/db/records/commit")
    public ResponseEntity<Object> commitToDB() {
        try {
            jpaRecordDetailsService.SaveToDB();
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("message", "Users Created To DataBase Successfully"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/db/records/commit/free")
    public ResponseEntity<Object> commitErrorFreeToDB() {
        try {
            jpaRecordDetailsService.saveErrorFreeToDB();
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("message", "Users Created Error Free To DataBase Successfully"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
