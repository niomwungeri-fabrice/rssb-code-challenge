package com.rssb.fileManager.controller;

import com.rssb.fileManager.exception.HttpResponseHandler;
import com.rssb.fileManager.model.Record;
import com.rssb.fileManager.service.ExcelService;
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

    @Autowired
    ExcelService fileService;


    @GetMapping("/redis/records")
    public ResponseEntity<Object> getUsers(@RequestParam(value="pageNumber" , required = false)Integer pageNumber, @RequestParam(value="pageSize" , required = false)Integer pageSize) {

        if(pageNumber == null) pageNumber = 1;
        if(pageSize == null) pageSize = 20;
        try {
            List<Record> records = Pagination.getPage((List<Record>) fileService.getAllUsers().get("data"), pageNumber, pageSize);
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }


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

    // DELETE FROM REDIS
    // INSERT ERROR FREE RECORDS ONLY
    // GET SPECIFIC RECORD


}
