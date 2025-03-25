package com.ysjz.pattern.mediator;

// ConcreteColleague: 具体同事类
class ConcreteUser extends User {
    public ConcreteUser(ChatRoom chatRoom, String name) {
        super(chatRoom, name);
    }

    @Override
    public void sendMessage(String message) {
        chatRoom.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " received: " + message);
    }
}

