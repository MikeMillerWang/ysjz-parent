package com.ysjz.entity;

// 基于上面的Animal和Dog，新增Cat子类
public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    // 重写eat方法
    @Override
    public void eat() {
        System.out.println(name + "在吃小鱼干");
    }

    // 子类特有方法
    public void meow() {
        System.out.println(name + "在喵喵叫");
    }
}
