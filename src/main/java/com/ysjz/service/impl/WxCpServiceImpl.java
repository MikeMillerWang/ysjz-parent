package com.ysjz.service.impl;

import com.sun.jndi.toolkit.url.UrlUtil;
import com.ysjz.service.WxCpService;
import com.ysjz.wx.cp.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 企业微信serviceImpl
 */
@Slf4j
@Service("wxCpService")
public class WxCpServiceImpl implements WxCpService {

    @Override
    public String verifyInterface(String msgSignature, String timestamp, String nonce, String echostr) {
        String sToken = "j2H7s9DlabHKnO8Vzd";
        String sCorpID = "wwb7d9511069b2713d";
        String sEncodingAESKey = "xAqHDaQCDoofDHuMevGwfs5urgaY4226UFjY5zBxCMC";

        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);

            // 进行 Urldecode 处理
            String sVerifyMsgSig = UrlUtil.decode(msgSignature, "UTF-8");
            String sVerifyTimeStamp = UrlUtil.decode(timestamp, "UTF-8");
            String sVerifyNonce = UrlUtil.decode(nonce, "UTF-8");
            String sVerifyEchoStr = UrlUtil.decode(echostr, "UTF-8");
            // 需要返回的明文
            return wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
        } catch (Exception e) {
            log.error("验证url报错", e);
        }

        return "验证url出错";
    }
}

