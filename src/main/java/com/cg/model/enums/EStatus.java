package com.cg.model.enums;

public enum EStatus {
    ROLE_STOCKING("EMPTY"),
    ROLE_OUT_OF_STOCK("BUSY");

    private final String value;

    EStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }


}
