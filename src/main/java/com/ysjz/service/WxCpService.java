package com.ysjz.service;

public interface WxCpService {

    String verifyInterface(String msgSignature, String timestamp, String nonce, String echostr);
}
