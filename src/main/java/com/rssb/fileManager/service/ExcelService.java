package com.rssb.fileManager.service;

import com.rssb.fileManager.model.Record;
import com.rssb.fileManager.repo.RecordRepo;
import com.rssb.fileManager.repo.RecordRepoRedis;
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

    RecordRepoRedis recordRepoRedis;
    RecordRepo recordRepo;

    @Autowired
    public ExcelService(RecordRepoRedis recordRepoRedis, RecordRepo recordRepo) {
        this.recordRepo = recordRepo;
        this.recordRepoRedis = recordRepoRedis;
    }

    public void SaveToDB() {
        try {
            List<Record> users = (List<Record>) this.getAllUsers().get("data");
            if (users==null)
                throw new RuntimeException("No Users found in the memory");
            recordRepo.saveAll(users);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void saveToRedis(MultipartFile file) {
        try {
            List<Record> users = ExcelHelper.excelParser(file.getInputStream());
            List<Record> validatedUsers = new ArrayList<>();
            for (Record user: users){
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
            recordRepoRedis.saveToRedis(validatedUsers);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Map<Object, Object> getAllUsers() {
        try {
           return recordRepoRedis.findFromRedis();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
