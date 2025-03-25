package com.ysjz.pattern.mediator;

import java.util.ArrayList;
import java.util.List;

// ConcreteMediator: 具体中介者
class ConcreteChatRoom implements ChatRoom {
    @Override
    public void sendMessage(String message, User user) {
        System.out.println(user.getName() + " sends message: " + message);
        // 中介者通知所有其他用户
        for (User u : users) {
            if (u != user) {
                u.receiveMessage(message);
            }
        }
    }

    private List<User> users = new ArrayList<>();

    // 注册用户
    public void addUser(User user) {
        users.add(user);
    }
}
