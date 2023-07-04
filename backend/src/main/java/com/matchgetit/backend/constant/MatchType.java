package com.matchgetit.backend.constant;

public enum MatchType {
    LEAGUE("리그"),
    FRIENDLY("친선");

    private final String value;

    MatchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}