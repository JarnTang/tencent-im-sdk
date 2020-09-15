package com.github.tencent.im.model.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.tencent.im.enums.AllowType;
import com.github.tencent.im.enums.ForbidType;
import com.github.tencent.im.enums.Gender;
import com.github.tencent.im.enums.MsgSetting;
import com.github.tencent.im.model.KvEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
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
    @JsonProperty("Tag_Profile_IM_Nick")
    private String nickName;
    @JsonProperty("Tag_Profile_IM_Gender")
    private Gender gender;
    @JsonProperty("Tag_Profile_IM_BirthDay")
    private Date birthday;
    @JsonProperty("Tag_Profile_IM_Location")
    private String location;
    @JsonProperty("Tag_Profile_IM_SelfSignature")
    private String signature;
    @JsonProperty("Tag_Profile_IM_AllowType")
    private AllowType allowType;
    @JsonProperty("Tag_Profile_IM_Language")
    private Integer language;
    @JsonProperty("Tag_Profile_IM_Image")
    private String image;
    @JsonProperty("Tag_Profile_IM_MsgSettings")
    private MsgSetting msgSetting;
    @JsonProperty("Tag_Profile_IM_AdminForbidType")
    private ForbidType forbidType;
    @JsonProperty("Tag_Profile_IM_Level")
    private Integer level;
    @JsonProperty("Tag_Profile_IM_Role")
    private Integer role;
    /**
     * 请参照腾讯云官方文档设置自定义字段
     *
     * @see <a href="https://cloud.tencent.com/document/product/269/1500#.E8.B5.84.E6.96.99.E5.AD.97.E6.AE.B5">腾讯官方文档链接</a>
     */
    private List<KvEntity> customData;

    public void addCustomData(KvEntity data) {
        if (customData == null) {
            customData = new ArrayList<>();
        }
        customData.add(data);
    }
}
