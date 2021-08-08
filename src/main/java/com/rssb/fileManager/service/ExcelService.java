package com.rssb.fileManager.service;

import com.rssb.fileManager.exception.HttpResponseHandler;
import com.rssb.fileManager.model.User;
import com.rssb.fileManager.repo.UserRepoRedis;
import com.rssb.fileManager.utils.ExcelHelper;
import com.rssb.fileManager.utils.UserErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ExcelService {

    UserRepoRedis userRepoRedis;

    @Autowired
    public ExcelService(UserRepoRedis userRepoRedis) {
        this.userRepoRedis = userRepoRedis;
    }

    public void save(MultipartFile file) {
        try {
            List<User> users = ExcelHelper.excelParser(file.getInputStream());
            Map<Map<String, User>, List<String>> errors = new HashMap<>();
            UserErrors userErrors = new UserErrors();
            for (User user: users){
                List<String> possibleErrors = new ArrayList<>();
                Map<String, User> userDetails = new HashMap<>();
                userDetails.put("user", user);
                if(user.getEmail().length()!=4){

                    possibleErrors.add("Invalid Email");
                }
                if(user.getGender().length()!=4){
                    possibleErrors.add("Invalid Gender");
                }
                errors.put(userDetails, possibleErrors);

                userErrors.setErrors(errors);
            }
            userRepoRedis.saveToRedis(userErrors);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public Map getAllUsers() {
        try {
           return userRepoRedis.findFromRedis();
        } catch (Exception e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
