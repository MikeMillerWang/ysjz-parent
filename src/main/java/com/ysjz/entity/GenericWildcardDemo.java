package com.ysjz.entity;

import java.util.ArrayList;
import java.util.List;

public class GenericWildcardDemo {
    // 通配符<?>：表示任意类型（只读，不能添加元素）
    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    // 上限通配符<? extends Animal>：只能是Animal或其子类
    public static void feedAnimals(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.eat();
        }
    }

    // 下限通配符<? super Dog2>：只能是Dog或其父类
    public static void addDog(List<? super Dog2> list) {
        list.add(new Dog2("旺财", "金毛")); // 可以添加Dog或其子类
    }

    public static void main(String[] args) {
        List<Dog2> dogs = new ArrayList<>();
        dogs.add(new Dog2("旺财", "金毛"));
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("咪宝"));

        // 通配符<?>
        printList(dogs); // 输出：Dog2@6f496d9f - 旺财
        printList(cats); // 输出：Cat@723279cf - 咪宝

        // 上限通配符<? extends Animal>
        feedAnimals(dogs); // 输出：旺财（金毛）在啃骨头
        feedAnimals(cats); // 输出：咪宝在吃小鱼干

        // 下限通配符<? super Dog>
        List<Animal> animals = new ArrayList<>();
        addDog(animals);
        System.out.println(animals.size()); // 输出：1
    }
}
