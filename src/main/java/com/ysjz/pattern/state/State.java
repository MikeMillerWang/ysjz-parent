package com.ysjz.pattern.state;

// 抽象状态类
public interface State {
    void handle(Context context);
}