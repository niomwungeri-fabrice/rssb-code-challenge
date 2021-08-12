package com.rssb.fileManager.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateDummy {
    static Logger logger = LoggerFactory.getLogger(GenerateDummy.class);

    private static String generateRandomStrings(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                + "lmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    private void writeBook(RssbRandamData aBook, Row row) {

        Cell cell = row.createCell(0);
        cell.setCellValue(aBook.getNames());
        cell = row.createCell(1);
        cell.setCellValue(aBook.getNationalId());
        cell = row.createCell(2);
        cell.setCellValue(aBook.getPhoneNumber());
        cell = row.createCell(3);
        cell.setCellValue(aBook.getGender());
        cell = row.createCell(4);
        cell.setCellValue(aBook.getEmail());
    }

    public void writeExcel(List<RssbRandamData> listBook, String excelFilePath) throws IOException, FileNotFoundException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;

        for (RssbRandamData aBook : listBook) {
            Row row = sheet.createRow(++rowCount);
            writeBook(aBook, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<RssbRandamData> getListBook() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("users");

        String[] validGender = {"Female", "Male", "MALE", "FEMALE", "female", "mail"};
        String[] validateMailHost = {"ymail.com", "gmail.com", "hotmail.com", "uza.rw"};

        List<RssbRandamData> listData = new ArrayList<>();
        int i = 1;
        while (i <= 50) {
            logger.debug("Row Number =>"+i);
            // Generate Random Gender
            int rnd = new Random().nextInt(validGender.length);
            String gender = validGender[rnd];
            // Generate Random Names
            String names = generateRandomStrings(4) + " " + generateRandomStrings(6).toUpperCase();
            // Generate Random email
            String email = generateRandomStrings(4).toLowerCase() + "." + generateRandomStrings(4).toLowerCase() + "@" + validateMailHost[new Random().nextInt(validateMailHost.length)];
            // Generate Random PhoneNumber
            int phone = ThreadLocalRandom.current().nextInt(1000000, 90000000 + 1);
            String phoneNumber = "07" + phone;
            // Generate Random NationId
            long nationalId = ThreadLocalRandom.current().nextLong(1000000000000000L, 9000000000000000L + 1);
            listData.add(new RssbRandamData(names, String.valueOf(nationalId), phoneNumber, gender, email));
            i++;
        }
        return listData;
    }

}
