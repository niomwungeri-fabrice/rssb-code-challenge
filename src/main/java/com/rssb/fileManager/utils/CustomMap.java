package com.rssb.fileManager.utils;

import com.rssb.fileManager.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomMap extends HashMap<String, List<User>> {

    private static final long serialVersionUID = 1L;

    public void put(String key, User number) {
        List<User> current = get(key);
        if (current == null) {
            current = new ArrayList<User>();
            super.put(key, current);
        }
        current.add(number);
    }

}
