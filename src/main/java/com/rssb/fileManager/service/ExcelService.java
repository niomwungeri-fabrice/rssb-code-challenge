package com.rssb.fileManager.service;

import com.rssb.fileManager.model.User;
import com.rssb.fileManager.repo.UserRepo;
import com.rssb.fileManager.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    UserRepo userRepo;

    public void save(MultipartFile file) {
        try {
            List<User> users = ExcelHelper.excelParser(file.getInputStream());

            userRepo.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
