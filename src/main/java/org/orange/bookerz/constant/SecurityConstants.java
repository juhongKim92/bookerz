package org.orange.bookerz.constant;

public enum SecurityConstants {

    JWT_KEY("encoding-key-test-key-test-test-test-test"),
    JWT_HEADER("Authorization");

    private final String value;

    SecurityConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
