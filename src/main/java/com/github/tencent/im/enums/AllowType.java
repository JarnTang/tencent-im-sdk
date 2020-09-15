package com.github.tencent.im.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * im allow type enum.
 *
 * @author changjiangtang
 * @since 2020-09-15 17:31:58
 */
@Getter
public enum AllowType implements Enums {
    /**
     * 需要经过自己确认才能添加自己为好友
     */
    NEED_CONFIRM("AllowType_Type_NeedConfirm"),
    /**
     * 允许任何人添加自己为好友
     */
    ALLOW_ANY("AllowType_Type_AllowAny"),
    /**
     * 不允许任何人添加自己为好友
     */
    DENY_ANY("AllowType_Type_DenyAny");

    @JsonValue
    private final String value;

    AllowType(String value) {
        this.value = value;
    }

}
