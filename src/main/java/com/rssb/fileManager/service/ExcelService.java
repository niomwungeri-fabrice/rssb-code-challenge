package com.rssb.fileManager.service;

import com.rssb.fileManager.model.User;
import com.rssb.fileManager.repo.UserRepo;
import com.rssb.fileManager.repo.UserRepoRedis;
import com.rssb.fileManager.utils.ExcelHelper;
import com.rssb.fileManager.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ExcelService {

    UserRepoRedis userRepoRedis;
    UserRepo userRepo;

    @Autowired
    public ExcelService(UserRepoRedis userRepoRedis, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.userRepoRedis = userRepoRedis;
    }

    public void SaveToDB() {
        try {
            List<User> users = (List<User>) this.getAllUsers().get("data");
            if (users==null)
                throw new RuntimeException("No Users found in the memory");
            userRepo.saveAll(users);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void saveToRedis(MultipartFile file) {
        try {
            List<User> users = ExcelHelper.excelParser(file.getInputStream());
            List<User> validatedUsers = new ArrayList<>();
            for (User user: users){
                List<String> possibleErrors = new ArrayList<>();
                if(!Validators.isValidEmailAddress(user.getEmail())){
                    possibleErrors.add("Invalid Email {"+user.getEmail()+"}");
                }
                if(!Validators.isGenderValid(user.getGender())){
                    possibleErrors.add("Invalid Gender {"+user.getGender()+"}");
                }
                if(Validators.isPhoneNumberValid(user.getPhoneNumber())){
                    possibleErrors.add("Invalid Phone {"+user.getPhoneNumber()+"}");
                }
                if(Validators.isNationalIdValid(user.getNationalId())){
                    possibleErrors.add("Invalid NID {"+user.getNationalId()+"}");
                }
                user.setErrors(possibleErrors);
                validatedUsers.add(user);
            }
            userRepoRedis.saveToRedis(validatedUsers);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Map<Object, Object> getAllUsers() {
        try {
           return userRepoRedis.findFromRedis();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
