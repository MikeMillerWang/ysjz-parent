package com.ysjz.util;

import lombok.extern.slf4j.Slf4j;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密算法工具类
 */
@Slf4j
public class EncryptUtils {

    public static String encodeURI(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            return str;
        }
    }

    public static String decodeURI(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            return str;
        }
    }

    /**
     * MD5加密算法(16位,ERP密码为16位)
     *
     * @param str
     * @return
     */
    public static String encodeMD5(String str) {
        String temp = encodemd5(str);
        if (temp != null) {
            return temp.substring(8, 24);// 16位的加密
        }
        return temp;
    }

    /**
     * MD5加密算法(32位)
     *
     * @param str
     * @return
     */
    public static String encodemd5(String str) {
        MessageDigest md = null;
        String dstr = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            dstr = byteArr2HexStr(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return dstr;// 32位加密
    }

    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16).toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 对字符串加密,加密算法使用SHA-256
     *
     * @param strSrc 要加密的字符串
     *               加密类型
     * @return
     * @throws Exception
     */
    public static String encodeSHA256(String strSrc) throws Exception {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            String encName = "SHA-256";
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = byteArr2HexStr(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * MD5 16进制加密
     */
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArr2HexStr(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArr2HexStr(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    /*** 
     * use MD5 to generate 32 bits MD5 code.
     * @Date:2016-01-12
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            log.error("string2MD5 error:" + e.getMessage(), e);
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 生成随机32位MD5串
     *
     * @return
     */
    public static String MD5Random() {
        return EncryptUtils.encodemd5(System.currentTimeMillis() + "" + Math.random() * 1000);
    }

    /**
     * MD5加密生成6位纯数字算法
     *
     * @param str
     * @return
     */
    public static String encode6LengthMD5(String str) {
        MessageDigest md = null;
        String result;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            result = byteArr2HexIntStr(md.digest()).substring(0, 6);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    public static String byteArr2HexIntStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(intTmp);
        }
        return sb.toString();
    }
}
