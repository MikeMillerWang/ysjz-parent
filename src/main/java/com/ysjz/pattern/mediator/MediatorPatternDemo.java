package com.ysjz.pattern.mediator;

// 客户端代码
public class MediatorPatternDemo {
    public static void main(String[] args) {
        ConcreteChatRoom chatRoom = new ConcreteChatRoom();

        User user1 = new ConcreteUser(chatRoom, "Alice");
        User user2 = new ConcreteUser(chatRoom, "Bob");
        User user3 = new ConcreteUser(chatRoom, "Charlie");

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        user1.sendMessage("Hi everyone!");
        user2.sendMessage("Hello Alice!");
    }
}
