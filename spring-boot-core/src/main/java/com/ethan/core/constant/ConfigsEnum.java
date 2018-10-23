package com.ethan.core.constant;

public enum ConfigsEnum {
    /**
     * @Description preffix
     **/
    PREFFIX("^^["),
    SUFFIX("]"),
    PRIVATE_KEY("certificate/Cert.jks"),
    PUBLIC_KEY("certificate/Public.csr"),
    KEY_PASS("certificate/keypass.text");

    private String value;

    private ConfigsEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
