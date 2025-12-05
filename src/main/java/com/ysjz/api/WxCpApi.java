package com.ysjz.api;

import cn.hutool.extra.servlet.ServletUtil;
import com.ysjz.service.WxCpMyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 企业微信Api
 */
@RestController
@RequestMapping("/wxCp")
public class WxCpApi {

    @Resource
    private WxCpMyService wxCpMyService;

    @RequestMapping("/verifyInterface")
    public String verifyInterface(@RequestParam("msg_signature") String msgSignature
            , @RequestParam("timestamp") String timestamp
            , @RequestParam("nonce") String nonce
            , @RequestParam(value = "echostr", required = false) String echostr
            , HttpServletRequest request) {
        return wxCpMyService.verifyInterface(msgSignature, timestamp, nonce, echostr, ServletUtil.getBody(request));
    }

    @RequestMapping("/verifyInterface2")
    public String verifyInterface2() {
        return "为妥妥递卖命";
    }
}
