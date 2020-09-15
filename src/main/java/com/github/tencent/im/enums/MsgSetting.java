package com.github.tencent.im.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * message setting enum.
 *
 * @author changjiangtang
 * @since 2020-09-15 17:35:40
 */
@Getter
public enum MsgSetting implements Enums {
    /**
     * 置0表示接收消息
     */
    @JsonProperty(index = 0)
    RECEIVE(0),
    /**
     * 置1则不接收消息
     */
    @JsonProperty(index = 1)
    DO_NOT_RECEIVE(1);

    @JsonValue
    private final Integer value;

    MsgSetting(int value) {
        this.value = value;
    }
}
