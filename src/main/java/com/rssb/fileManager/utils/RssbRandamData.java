package com.rssb.fileManager.utils;


public class RssbRandamData {
        private String names;
        private String nationalId;
        private String phoneNumber;
        private String gender;
        private String email;

        public RssbRandamData() {

        }

        public RssbRandamData(String names, String nationalId, String phoneNumber, String gender, String email) {
            this.names = names;
            this.nationalId = nationalId;
            this.phoneNumber = phoneNumber;
            this.gender = gender;
            this.email = email;
        }

        public String getNames() {
            return names;
        }

        public void setNames(String names) {
            this.names = names;
        }

        public String getNationalId() {
            return nationalId;
        }

        public void setNationalId(String nationalId) {
            this.nationalId = nationalId;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

