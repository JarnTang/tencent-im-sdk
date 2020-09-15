package com.github.tencent.im.model.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.tencent.im.model.KvEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * profile entity.
 *
 * @author changjiangtang
 * @since 2020-09-15 16:05:48
 */
@Data
public class Profile {
    @JsonProperty("From_Account")
    private String accountId;
    @JsonProperty("ProfileItem")
    private List<KvEntity> profiles;

    public void addProfile(KvEntity entity){
        if (profiles == null) {
            profiles = new ArrayList<>();
        }
        profiles.add(entity);
    }
}
