package com.ysjz.pattern.state;

// 具体状态类A
public class ConcreteStateA implements State {
    @Override
    public void handle(Context context) {
        System.out.println("Handling request in State A.");
        context.setState(new ConcreteStateB());
    }
}