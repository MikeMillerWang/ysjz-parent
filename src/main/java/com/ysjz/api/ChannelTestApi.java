package com.ysjz.api;

import com.ysjz.service.impl.ChannelTestServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("channelTest")
public class ChannelTestApi {

    @Resource
    private ChannelTestServiceImpl channelTestServiceImpl;

    @PostMapping("/add")
    public String add() {
        return channelTestServiceImpl.add();
    }
}