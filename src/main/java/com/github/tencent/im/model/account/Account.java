package com.github.tencent.im.model.account;

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
    private String id;
    private String nickName;
    private String faceUrl;
}
