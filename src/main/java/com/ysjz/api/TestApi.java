package com.ysjz.api;

import cn.hutool.core.net.url.UrlBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testApi")
public class TestApi {

    @PostMapping("/test1")
    public String test1() {
        return UrlBuilder.ofHttp("https://www.baidu.com").build();
    }
}
