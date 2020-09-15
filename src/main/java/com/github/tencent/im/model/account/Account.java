package com.github.tencent.im.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * im account info.
 *
 * @author changjiangtang
 * @since 2020-09-15 14:54:49
 */
@Data
@Accessors(chain = true)
public class Account {
    @JsonProperty("Identifier")
    private String id;
    @JsonProperty("Nick")
    private String nickName;
    @JsonProperty("FaceUrl")
    private String faceUrl;
}
