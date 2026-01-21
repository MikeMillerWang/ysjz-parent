package com.ysjz.entity;

import com.ysjz.service.AnimalBehavior;

// 2. 实现类：必须实现接口的所有抽象方法
public class Dog1 implements AnimalBehavior {
    @Override
    public void eat() {
        System.out.println("狗啃骨头");
    }

    @Override
    public void move() {
        System.out.println("狗跑");
    }

    // 可选：重写接口的默认方法
    @Override
    public void breathe() {
        System.out.println("狗用鼻子呼吸");
    }

    public static void main(String[] args) {
        Dog1 dog1 = new Dog1();
        dog1.eat();    // 输出：狗啃骨头
        dog1.move();   // 输出：狗跑
        dog1.breathe();// 输出：狗用鼻子呼吸
    }
}
