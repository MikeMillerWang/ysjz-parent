package com.ysjz.games;

import java.util.Scanner;
import java.util.Random;

public class GuessNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // 生成1-100的随机数
        int secretNumber = random.nextInt(100) + 1;
        int guess;
        int attempts = 0;
        boolean hasWon = false;

        System.out.println("欢迎来到猜数字游戏！");
        System.out.println("我已经想好了一个1到100之间的数字，你能猜出来吗？");

        // 最多给7次机会
        while (attempts < 7) {
            System.out.print("请输入你的猜测（还剩" + (7 - attempts) + "次机会）: ");

            // 验证输入是否为数字
            while (!scanner.hasNextInt()) {
                System.out.println("请输入一个有效的数字！");
                scanner.next(); // 清除无效输入
            }

            guess = scanner.nextInt();
            attempts++;

            if (guess < secretNumber) {
                System.out.println("太小了！");
            } else if (guess > secretNumber) {
                System.out.println("太大了！");
            } else {
                hasWon = true;
                break;
            }
        }

        if (hasWon) {
            System.out.println("恭喜你！你用了" + attempts + "次猜对了数字！");
        } else {
            System.out.println("游戏结束！正确的数字是：" + secretNumber);
        }

        scanner.close();
    }
}
