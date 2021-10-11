package com.aspireapp.data;

public enum AcessRoles {
    ADMIN("Admin"),FINANCE("Finance"),EMPLOYEE("Employee");
    private String value;
    AcessRoles(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
