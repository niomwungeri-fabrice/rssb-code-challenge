package com.rssb.fileManager.exception;

import java.util.HashMap;

public class HttpResponseHandler {
    public static HashMap<String, Object> responseHandler(String message, Object value) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put(message, value);
        return hashMap;
    }
}
