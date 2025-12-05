package com.ysjz.service;

public interface WxCpMyService {

    String verifyInterface(String msgSignature, String timestamp, String nonce, String echostr, String reqBody);
}
