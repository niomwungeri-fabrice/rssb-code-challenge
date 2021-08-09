package com.rssb.fileManager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private final UUID userId = UUID.randomUUID();
    @Column(name = "names")
    private String names;
    @Column(name = "nid")
    private String  nationalId;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "gender")
    private String gender;
    @Column(name = "email")
    private String email;
    @Transient
    private List<String> errors;

    public User() {
    }

//    public User(String names, String nationalId, String phoneNumber, String gender, String email) {
//        this.names = names;
//        this.nationalId = nationalId;
//        this.phoneNumber = phoneNumber;
//        this.gender = gender;
//        this.email = email;
//    }

    public UUID getUserId() {
        return userId;
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

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", names='" + names + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", errors=" + errors +
                '}';
    }
}
