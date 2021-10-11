package com.aspireapp.data;

public enum Roles {
    DIRECTOR("Director (according to ACRA)"),NON_DIRECTOR("Non-director");
    private String value;
    Roles(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
