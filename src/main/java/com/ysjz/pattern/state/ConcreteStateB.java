package com.ysjz.pattern.state;

// 具体状态类B
public class ConcreteStateB implements State {
    @Override
    public void handle(Context context) {
        System.out.println("Handling request in State B.");
        context.setState(new ConcreteStateA());
    }
}