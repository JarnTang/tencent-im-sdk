package com.github.tencent.im;

import com.github.tencent.util.JsonUtil;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Deflater;

/**
 * tencent im cloud sign sdk.
 *
 * @author changjiangtang
 * @since 2020-09-15 11:04:00
 */
public class TencentImSignSdk {

    private final Long appId;
    private final String appSecret;

    public TencentImSignSdk(Long appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public String genSign(String identifier, long expire) {
        return genSig(identifier, expire, null);
    }

    public String genSign(String identifier, long expire, byte[] userBuf) {
        return genSig(identifier, expire, userBuf);
    }

    private String genSig(String identifier, long expire, byte[] userBuf) {
        long currTime = System.currentTimeMillis() / 1000;
        Map<String, Object> sigDoc = new HashMap<>();
        sigDoc.put("TLS.ver", "2.0");
        sigDoc.put("TLS.identifier", identifier);
        sigDoc.put("TLS.sdkappid", appId);
        sigDoc.put("TLS.expire", expire);
        sigDoc.put("TLS.time", currTime);

        String base64UserBuf = null;
        if (null != userBuf) {
            base64UserBuf = Base64.encodeBase64String(userBuf);
            sigDoc.put("TLS.userbuf", base64UserBuf);
        }
        String sig = hmacsha256(identifier, currTime, expire, base64UserBuf);
        if (sig.length() == 0) {
            return "";
        }
        sigDoc.put("TLS.sig", sig);
        Deflater compressor = new Deflater();
        compressor.setInput(JsonUtil.toJson(sigDoc).getBytes(StandardCharsets.UTF_8));
        compressor.finish();
        byte[] compressedBytes = new byte[2048];
        int compressedBytesLength = compressor.deflate(compressedBytes);
        compressor.end();
        return base64EncodeUrl(Arrays.copyOfRange(compressedBytes, 0, compressedBytesLength))
                .replaceAll("\\s*", "");
    }

    private String hmacsha256(String identifier, long currTime, long expire, String base64UserBuf) {
        String LINE_SEPARATOR = System.lineSeparator();
        String beSignedContent = "TLS.identifier:" + identifier + LINE_SEPARATOR +
                "TLS.sdkappid:" + appId + LINE_SEPARATOR +
                "TLS.time:" + currTime + LINE_SEPARATOR +
                "TLS.expire:" + expire + LINE_SEPARATOR;
        if (null != base64UserBuf) {
            beSignedContent += "TLS.userbuf:" + base64UserBuf + "\n";
        }
        try {
            byte[] byteKey = appSecret.getBytes(StandardCharsets.UTF_8);
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(byteKey, "HmacSHA256");
            hmac.init(keySpec);
            byte[] byteSig = hmac.doFinal(beSignedContent.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(byteSig).replaceAll("\\s*", "");
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private static String base64EncodeUrl(byte[] input) {
        byte[] base64 = Base64.encodeBase64(input);
        for (int i = 0; i < base64.length; ++i) {
            switch (base64[i]) {
                case '+':
                    base64[i] = '*';
                    break;
                case '/':
                    base64[i] = '-';
                    break;
                case '=':
                    base64[i] = '_';
                    break;
                default:
                    break;
            }
        }
        return new String(base64);
    }

}
