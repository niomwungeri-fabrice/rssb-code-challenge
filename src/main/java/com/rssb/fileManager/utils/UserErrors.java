package com.rssb.fileManager.utils;

import com.rssb.fileManager.model.User;
import org.apache.catalina.LifecycleState;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserErrors implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<Map<String, User>, List<String>> errors;

    public Map<Map<String, User>, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<Map<String, User>, List<String>> errors) {
        this.errors = errors;
    }
}
