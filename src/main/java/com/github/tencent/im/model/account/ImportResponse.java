package com.github.tencent.im.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.tencent.im.model.SdkResponse;
import lombok.Data;

import java.util.List;

/**
 * import account response entity.
 *
 * @author changjiangtang
 * @since 2020-09-15 15:38:55
 */
@Data
public class ImportResponse extends SdkResponse {
    @JsonProperty("FailAccounts")
    private List<String> failAccounts;

}
