package com.github.tencent.im.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * kv entity.
 *
 * @author changjiangtang
 * @since 2020-09-15 16:04:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KvEntity {
    @JsonProperty("Tag")
    private String key;
    @JsonProperty("Value")
    private Object value;
}
