package com.ysjz.api;

import com.ysjz.service.WxCpService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 企业微信Api
 */
@RestController
@RequestMapping("/visit/wxCp")
public class WxCpApi {

    @Resource
    private WxCpService wxCpService;

    @RequestMapping("/verifyInterface")
    public String verifyInterface(@RequestParam("msg_signature") String msgSignature
            , @RequestParam("timestamp") String timestamp
            , @RequestParam("nonce") String nonce
            , @RequestParam("echostr") String echostr) {
        return wxCpService.verifyInterface(msgSignature, timestamp, nonce, echostr);
    }

    @RequestMapping("/verifyInterface2")
    public String verifyInterface2() {
        return "为妥妥递卖命";
    }
}
