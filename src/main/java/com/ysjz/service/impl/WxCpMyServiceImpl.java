package com.ysjz.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.sun.jndi.toolkit.url.UrlUtil;
import com.ysjz.service.WxCpMyService;
import com.ysjz.wx.cp.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 企业微信serviceImpl
 */
@Slf4j
@Service("wxCpMyService")
public class WxCpMyServiceImpl implements WxCpMyService {

    @Resource
    private WxCpService wxCpService;

    @Override
    public String verifyInterface(String msgSignature, String timestamp, String nonce, String echostr, String reqBody) {
        log.info("参数：msgSignature={}, timestamp={}, nonce={}, echostr={}, reqBody={}", msgSignature, timestamp, nonce, echostr, reqBody);

        String sToken = "j2H7s9DlabHKnO8Vzd";
        String sCorpID = "wwb7d9511069b2713d";
        String sEncodingAESKey = "xAqHDaQCDoofDHuMevGwfs5urgaY4226UFjY5zBxCMC";

        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
            // 进行 Urldecode 处理
            String sVerifyMsgSig = UrlUtil.decode(msgSignature, "UTF-8");
            String sVerifyTimeStamp = UrlUtil.decode(timestamp, "UTF-8");
            String sVerifyNonce = UrlUtil.decode(nonce, "UTF-8");

            if (StrUtil.isNotBlank(echostr)) {
                String sVerifyEchoStr = UrlUtil.decode(echostr, "UTF-8");
                // 需要返回的明文
                return wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
            } else {
                // String sMsg = wxcpt.DecryptMsg(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, reqBody);
                // String sMsg = "<xml><ToUserName><![CDATA[wwb7d9511069b2713d]]></ToUserName><FromUserName><![CDATA[sys]]></FromUserName><CreateTime>1760421817</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[change_external_contact]]></Event><ChangeType><![CDATA[add_external_contact]]></ChangeType><UserID><![CDATA[wangzhu]]></UserID><ExternalUserID><![CDATA[wmHK-NDQAANk9DvttKFlyUtOJ6Yoo5Bw]]></ExternalUserID><State><![CDATA[zhangsan]]></State><WelcomeCode><![CDATA[Mu_TTUdGq9ZJSj6Rs-HBh8cWrjt3PeHdc4M7gzeCO34]]></WelcomeCode></xml>";

                WxCpXmlMessage wxCpXmlMessage = WxCpXmlMessage.fromEncryptedXml(reqBody, wxCpService.getWxCpConfigStorage(), timestamp, nonce, msgSignature);
                log.info("解析后的消息：{}", JSONUtil.toJsonStr(wxCpXmlMessage));
            }
        } catch (Exception e) {
            log.error("验证url报错", e);
        }

        return "验证url出错";
    }
}

