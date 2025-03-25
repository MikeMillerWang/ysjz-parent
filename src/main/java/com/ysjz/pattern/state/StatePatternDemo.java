package com.ysjz.pattern.state;

// 客户端代码
public class StatePatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());
        context.request(); // 输出: Handling request in State A.
        context.request(); // 输出: Handling request in State B.
    }
}