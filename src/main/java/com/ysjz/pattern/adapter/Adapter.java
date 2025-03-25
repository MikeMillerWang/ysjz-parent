package com.ysjz.pattern.adapter;

// 适配器
public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        System.out.println("Adapter: Translating request...");
        adaptee.specificRequest(); // 转发请求
    }
}
