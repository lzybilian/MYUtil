package com.lzy.myutil;

import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author 刘智渊
 * @time 2021/1/13 16:14
 * @describe 编码工具类
 */
public class EncodeUtil {

    /**
     * 以UTF-8 来编码
     * @param input The input.
     * @return the urlencoded string
     */
    public static String urlEncode( String input) {
        return urlEncode(input, "UTF-8");
    }

    /**
     *  编码
     * @param input       The input.
     * @param charsetName 编码格式
     * @return the urlencoded string
     */
    public static String urlEncode( String input,  String charsetName) {
        if (TextUtils.isEmpty(input)) {
            return "";
        }
        try {
            return URLEncoder.encode(input, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    /**
     *
     *以UTF-8 来解码
     * @param input The input.
     * @return the string of decode urlencoded string
     */
    public static String urlDecode( String input) {
        return urlDecode(input, "UTF-8");
    }

    /**
     * 解码
     * @param input       The input.
     * @param charsetName The name of charset.
     * @return the string of decode urlencoded string
     */
    public static String urlDecode( String input,  String charsetName) {
        if (TextUtils.isEmpty(input)) {
            return "";
        }
        try {
            String safeInput = input.replaceAll("%(?![0-9a-fA-F]{2})", "%25").replaceAll("\\+", "%2B");
            return URLDecoder.decode(safeInput, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * base64编码
     * @param input The input.
     * @return Base64-encode bytes
     */
    public static byte[] base64Encode( String input) {
        return base64Encode(input.getBytes());
    }

    /**
     * base64编码
     * @param input The input.
     * @return Base64-encode bytes
     */
    public static byte[] base64Encode( byte[] input) {
        if (input == null || input.length == 0) return new byte[0];
        return Base64.encode(input, Base64.NO_WRAP);
    }

    /**
     * base64编码
     * @param input The input.
     * @return Base64-encode string
     */
    public static String base64EncodeString( byte[] input) {
        if (input == null || input.length == 0) return "";
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }

    /**
     * base64解码
     * @param input The input.
     * @return the string of decode Base64-encode string
     */
    public static byte[] base64Decode(final String input) {
        if (input == null || input.length() == 0) return new byte[0];
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * base64解码
     * @param input The input.
     * @return the bytes of decode Base64-encode bytes
     */
    public static byte[] base64Decode(final byte[] input) {
        if (input == null || input.length == 0) return new byte[0];
        return Base64.decode(input, Base64.NO_WRAP);
    }
}
