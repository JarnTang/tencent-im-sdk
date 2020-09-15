package com.github.tencent.im;

import com.github.tencent.im.constant.ApiNames;
import com.github.tencent.im.model.account.Account;
import com.github.tencent.im.model.account.ImportResponse;
import com.github.tencent.im.model.profile.Profile;
import com.github.tencent.im.model.profile.ProfileResponse;
import com.github.tencent.util.JsonUtil;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * tencent cloud im sdk.
 *
 * @author changjiangtang
 * @since 2020-09-15 11:02:09
 */
public class TencentImSdk {
    private final Long appId;
    private final String appSecret;
    private final String adminUserId;

    TencentImSignSdk imSignSdk;
    private static final int EXPIRE = 60 * 60 * 2;
    private final OkHttpClient httpClient = new OkHttpClient();
    private final static String TENCENT_DOMAIN = "https://console.tim.qq.com/";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public TencentImSdk(Long appId, String appSecret, String adminUserId) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.adminUserId = adminUserId;
        imSignSdk = new TencentImSignSdk(appId, appSecret);
    }

    public ImportResponse importAccount(Account account) throws IOException {
        return createRequest(ApiNames.IMPORT_ACCOUNT, account, ImportResponse.class);
    }

    public ImportResponse batchImportAccount(List<String> accounts) throws IOException {
        return createRequest(ApiNames.BATCH_IMPORT_ACCOUNT, accounts, ImportResponse.class);
    }

    public ProfileResponse updateProfile(Profile profile) throws IOException {
        return createRequest(ApiNames.UPDATE_PROFILE, profile, ProfileResponse.class);
    }

    @NotNull
    private <T> T createRequest(String apiName, Object params, Class<T> clazz) throws IOException {
        String url = buildRequestUrl(apiName);
        String paramJson = JsonUtil.toJson(params);
        RequestBody requestBody = RequestBody.create(paramJson, JSON);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try (Response response = httpClient.newCall(request).execute()) {
            String responseJson = Objects.requireNonNull(response.body()).string();
            return JsonUtil.parseObject(responseJson, clazz);
        }
    }

    private String buildRequestUrl(String apiName) {
        int random = (int) System.currentTimeMillis() / 1000;
        String sign = imSignSdk.genSign(adminUserId, EXPIRE);
        String uri = String.format("?sdkappid=%s&identifier=%s&usersig=%s&random=%s&contenttype=json",
                appId, adminUserId, sign, random);
        return TENCENT_DOMAIN + apiName + uri;
    }

}