package com.ysjz.entity;

// 子类：Dog2（继承Animal）
public class Dog2 extends Animal {
    // 子类特有属性
    private String breed; // 品种

    // 子类构造方法：必须通过super调用父类构造
    public Dog2(String name, String breed) {
        super(name); // 调用父类的构造方法
        this.breed = breed;
    }

    // 重写（Override）父类方法：扩展/修改父类行为
    @Override
    public void eat() {
        System.out.println(name + "（" + breed + "）在啃骨头");
    }

    // 子类特有方法：扩展父类功能
    public void bark() {
        System.out.println(name + "在汪汪叫");
    }
}
