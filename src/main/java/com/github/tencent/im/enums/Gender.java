package com.github.tencent.im.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * gender enum.
 *
 * @author changjiangtang
 * @since 2020-09-15 17:26:35
 */
@Getter
public enum Gender implements Enums {
    /**
     * 未设置性别
     */
    UNKNOWN("Gender_Type_Unknown"),
    /**
     * 女性
     */
    MALE("Gender_Type_Female"),
    /**
     * 男性
     */
    FEMALE("Gender_Type_Male");

    @JsonValue
    private final String value;

    Gender(String value) {
        this.value = value;
    }

}
