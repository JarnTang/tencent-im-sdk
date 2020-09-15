package com.github.tencent.im.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * tencent api response entity.
 *
 * @author changjiangtang
 * @since 2020-09-15 14:29:34
 */
@Data
public class SdkResponse {

    @JsonProperty("ActionStatus")
    private String status;
    @JsonProperty("ErrorCode")
    private Integer errorCode;
    @JsonProperty("ErrorInfo")
    private String errorMsg;

}
