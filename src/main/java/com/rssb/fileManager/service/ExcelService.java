package com.rssb.fileManager.service;

import com.rssb.fileManager.model.Record;
import com.rssb.fileManager.repo.RepoRedis;
import com.rssb.fileManager.utils.ExcelHelper;
import com.rssb.fileManager.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    RepoRedis repoRedis;


    public void saveToRedis(MultipartFile file) {
        try {
            List<Record> records = ExcelHelper.excelParser(file.getInputStream());
            List<Record> validatedRecords = new ArrayList<>();
            for (Record record: records){
                List<String> possibleErrors = new ArrayList<>();
                if(!Validators.isValidEmailAddress(record.getEmail())){
                    possibleErrors.add("Invalid Email {"+record.getEmail()+"}");
                }
                if(!Validators.isGenderValid(record.getGender())){
                    possibleErrors.add("Invalid Gender {"+record.getGender()+"}");
                }
                if(Validators.isPhoneNumberValid(record.getPhoneNumber())){
                    possibleErrors.add("Invalid Phone {"+record.getPhoneNumber()+"}");
                }
                if(Validators.isNationalIdValid(record.getNationalId())){
                    possibleErrors.add("Invalid NID {"+record.getNationalId()+"}");
                }
                record.setErrors(possibleErrors);
                validatedRecords.add(record);
            }
            repoRedis.saveToRedis(validatedRecords);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
