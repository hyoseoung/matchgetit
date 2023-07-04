package com.matchgetit.backend.constant;

public enum LogInType {
    NORMAL("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER");

    private final String authority;

    LogInType(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}