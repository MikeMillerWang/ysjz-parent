package com.ysjz.entity;

// 自定义泛型类：Box<T>，T是类型参数（可以任意命名，常用T/E/K/V）
public class Box<T> {
    private T content;

    // 泛型方法：设置内容
    public void setContent(T content) {
        this.content = content;
    }

    // 泛型方法：获取内容
    public T getContent() {
        return content;
    }

    // 测试泛型类
    public static void main(String[] args) {
        // 1. 指定T为String类型
        Box<String> stringBox = new Box<>();
        stringBox.setContent("Hello 泛型");
        String str = stringBox.getContent(); // 无需强制转换
        System.out.println(str); // 输出：Hello 泛型

        // 2. 指定T为Integer类型
        Box<Integer> integerBox = new Box<>();
        integerBox.setContent(100);
        Integer num = integerBox.getContent();
        System.out.println(num); // 输出：100

        // 3. 类型安全：编译时检查，避免装错类型
        // stringBox.setContent(100); // 编译错误：不能将Integer赋值给String类型的Box
    }
}
