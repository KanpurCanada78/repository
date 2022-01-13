package com.helper;

public enum EmailClientType {
    GMAIL("Gmail"),
    OUTLOOK("Outlook");

    EmailClientType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return this.value;
    }
}