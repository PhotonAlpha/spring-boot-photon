package com.ethan.common.constant;

/**
 * @version 1.0
 * @date 05/03/2019
 */
public enum AppTypeEnum {
    TYPE_EDUCATE("教育"), TYPE_ENTERTAINMENT("娱乐"), TYPE_SOCIAL("社交"), TYPE_OTHERS("其他");

    private String description;

    AppTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
