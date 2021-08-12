package com.rssb.fileManager.controller;

import com.rssb.fileManager.exception.HttpResponseHandler;
import com.rssb.fileManager.model.Record;
import com.rssb.fileManager.service.RedisService;
import com.rssb.fileManager.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @DeleteMapping("/records")
    public ResponseEntity<?> getAllRecords() {
        try {
            redisService.deleteAllRedisRecords();
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("message", "Records Deleted From Memory Successfully"),
                    HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/records")
    public ResponseEntity<Object> getRecords(@RequestParam(value="pageNumber" , required = false)Integer pageNumber, @RequestParam(value="pageSize" , required = false)Integer pageSize) {

        if(pageNumber == null) pageNumber = 1;
        if(pageSize == null) pageSize = 20;
        try {
            List<Record> records = Pagination.getPage((List<Record>) redisService.getRedisRecords().get("data"), pageNumber, pageSize);

            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
