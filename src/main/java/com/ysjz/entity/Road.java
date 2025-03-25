package com.ysjz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Road {

    /**
     * 名称
     */
    private String name;

    /**
     * 道路长度
     */
    private double len;

    /**
     * 社区划分
     */
    private String community;
}
