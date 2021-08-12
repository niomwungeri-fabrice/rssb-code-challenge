package com.rssb.fileManager.controller;


import com.rssb.fileManager.exception.HttpResponseHandler;
import com.rssb.fileManager.model.Record;
import com.rssb.fileManager.service.ExcelService;
import com.rssb.fileManager.utils.ExcelHelper;
import com.rssb.fileManager.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/v1")
public class ExcelController {
    @Autowired
    ExcelService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.saveToRedis(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return new ResponseEntity<>(HttpResponseHandler.responseHandler("message", message),
                        HttpStatus.OK);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", message),
                        HttpStatus.BAD_REQUEST);
            }
        }
        message = "Please upload an excel file!";
        return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", message),
                HttpStatus.BAD_REQUEST);
    }

}

