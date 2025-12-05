package com.ysjz.wx.cp;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxCpConfig {

    @Bean
    public WxCpService wxCpService() {
        WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
        // 设置企业微信相关配置
        config.setCorpId("wwb7d9511069b2713d");
        config.setCorpSecret("uzIIe-0Fm83vg-kZr4YLG3XvHeTEC8UCtvm3Va-pJaE");

        config.setToken("j2H7s9DlabHKnO8Vzd");
        config.setAesKey("xAqHDaQCDoofDHuMevGwfs5urgaY4226UFjY5zBxCMC");

        WxCpService wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(config);
        return wxCpService;
    }
}
