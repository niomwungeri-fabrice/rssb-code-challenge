package com.rssb.fileManager.controller;


import com.rssb.fileManager.exception.HttpResponseHandler;
import com.rssb.fileManager.model.User;
import com.rssb.fileManager.response.ResponseMessage;
import com.rssb.fileManager.service.ExcelService;
import com.rssb.fileManager.utils.ExcelHelper;
import com.rssb.fileManager.utils.Pagination;
import com.rssb.fileManager.utils.UserErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    ExcelService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                e.printStackTrace();
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getUsers() {
        try {
//            List<User> nonPaginatedList = (List<User>) fileService.getAllUsers().get("data");
//            List<User> users = Pagination.getPage(nonPaginatedList, 1, 1);
            return new ResponseEntity<>(fileService.getAllUsers().get("data"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", e.getMessage()),
                    HttpStatus.OK);
        }
    }
}

