package com.luckyqing.sso.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: notice
 * @description: md5工具类
 * @author: "清歌"
 * @create: 2018-08-21 12:02
 **/
public class MD5Utils {

    public static String getMD5(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getHashString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getHashString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }
}
