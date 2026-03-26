package com.ysjz.api;

import cn.hutool.core.net.url.UrlBuilder;
import com.ysjz.entity.BigDecimalTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/testApi")
public class TestApi {

    @PostMapping("/test1")
    public String test1() {
        return UrlBuilder.ofHttp("https://www.baidu.com").build();
    }

    @PostMapping("/test2")
    public BigDecimalTest test2() {
        BigDecimal b1 = new BigDecimal(1.23);
        BigDecimalTest bt = new BigDecimalTest();
        bt.setBd(b1);
        return bt;
    }
}