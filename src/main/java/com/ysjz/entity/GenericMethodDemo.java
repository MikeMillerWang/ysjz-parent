package com.ysjz.entity;

public class GenericMethodDemo {
    // 泛型方法：<T>是方法的类型参数，T[]是参数类型，T是返回类型
    public static <T> T getFirstElement(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    public static void main(String[] args) {
        String[] strArray = {"Java", "泛型", "教程"};
        Integer[] intArray = {1, 2, 3, 4};

        // 调用泛型方法，自动推断类型
        String firstStr = getFirstElement(strArray);
        Integer firstInt = getFirstElement(intArray);

        System.out.println(firstStr); // 输出：Java
        System.out.println(firstInt); // 输出：1
    }
}
