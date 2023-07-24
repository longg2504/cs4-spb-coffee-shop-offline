package com.cg.model.enums;

public enum EStatus {
    ROLE_STOCKING("Rãnh"),
    ROLE_OUT_OF_STOCK("Bận");

    private final String value;

    EStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }


}
