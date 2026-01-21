package com.ysjz.entity;

// 父类：抽象的"动物"
public class Animal {
    // 父类属性
    protected String name; // protected：子类可直接访问

    // 父类构造方法
    public Animal(String name) {
        this.name = name;
    }

    // 父类方法
    public void eat() {
        System.out.println(name + "在吃食物");
    }

    // 父类方法
    public void sleep() {
        System.out.println(name + "在睡觉");
    }
}
