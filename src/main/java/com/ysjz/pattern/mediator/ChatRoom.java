package com.ysjz.pattern.mediator;

// Mediator: 中介者接口
interface ChatRoom {
    void sendMessage(String message, User user);
}
