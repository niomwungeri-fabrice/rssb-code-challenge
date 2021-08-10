package com.rssb.fileManager.controller;

import com.rssb.fileManager.model.RootModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/")
    public ResponseEntity<RootModel> getAllTutorials() {
        try {
            return new ResponseEntity<>( new RootModel("Welcome to File handler API"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
