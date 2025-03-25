package com.ysjz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysjz.entity.ChannelTest;
import com.ysjz.mapper.ChannelTestMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChannelTestServiceImpl extends ServiceImpl<ChannelTestMapper, ChannelTest> {

    @Transactional(rollbackFor = Exception.class)
    public String add() {
        // 循环批量添加数据
        int count = 1;
        List<ChannelTest> list = new ArrayList<>();
        while(true) {
            ChannelTest channelTest = new ChannelTest();
            channelTest.setLinkmanName("" + count);

            list.add(channelTest);
            if (count % 5000 == 0) {
                this.saveBatch(list);
                list.clear();
            }
            if (count > 100000) {
                break;
            }
            count++;
        }

        return "添加成功";
    }
}