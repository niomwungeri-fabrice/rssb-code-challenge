package com.rssb.fileManager.enums;

public enum UserRole {

    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String roleAssigned;

    private UserRole(String roleAssigned) {
        this.roleAssigned = roleAssigned;
    }

    @Override
    public String toString() {
        return roleAssigned;
    }
}