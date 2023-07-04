package com.matchgetit.backend.constant;
public enum AccountState {
    ACTIVE("활동 중"),
    BANNED("정지"),
    DORMANT("휴면");

    private final String value;

    AccountState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
