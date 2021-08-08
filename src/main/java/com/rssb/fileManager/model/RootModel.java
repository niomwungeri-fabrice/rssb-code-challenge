package com.rssb.fileManager.model;

import java.util.Date;

public class RootModel {
    private String message;
    private Date date = new Date();

    public RootModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
