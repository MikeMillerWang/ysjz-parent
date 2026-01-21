package com.ysjz.service;

// 1. 定义接口：封装行为规范（只有方法签名，无实现）
public interface AnimalBehavior {
    // 抽象方法：无方法体
    void eat();

    void move();

    // 默认方法（JDK8+）：有默认实现，实现类可重写
    default void breathe() {
        System.out.println("动物呼吸空气");
    }
}
