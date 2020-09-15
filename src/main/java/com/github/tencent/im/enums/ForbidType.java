package com.github.tencent.im.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * forbid type enum.
 *
 * @author changjiangtang
 * @since 2020-09-15 17:38:38
 */
@Getter
public enum ForbidType implements Enums {
    /**
     * 默认值，允许加好友
     */
    none("AdminForbid_Type_None"),
    /**
     * 禁止该用户发起加好友请求
     */
    send_out("AdminForbid_Type_SendOut"),
    ;

    @JsonValue
    private final String value;

    ForbidType(String value) {
        this.value = value;
    }

}
