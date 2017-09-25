package com.session.servlet;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Description:
 * Date:2017/9/21 13:42
 * Author:cjx
 */
public class TokenProcessor {
    //单例模式
    private TokenProcessor() {
    }
    private static final TokenProcessor instance = new TokenProcessor();
    public static TokenProcessor getInstance() {
        return instance;
    }
    //生成token
    public String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());
            //base64--任意二进制编码明文字符
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
