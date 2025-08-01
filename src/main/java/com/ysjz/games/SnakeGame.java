package com.ysjz.games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakeGame extends JFrame {

    private static final int TILE_SIZE = 25; // 每个格子的大小
    private static final int WIDTH = 20;    // 游戏区域宽度（格子数）
    private static final int HEIGHT = 20;   // 游戏区域高度（格子数）
    private static final int ALL_TILES = WIDTH * HEIGHT;
    private static final int DELAY = 150;   // 游戏速度（毫秒）

    private final int[] x = new int[ALL_TILES]; // 蛇的x坐标
    private final int[] y = new int[ALL_TILES]; // 蛇的y坐标

    private int snakeLength; // 蛇的长度
    private int foodX;      // 食物的x坐标
    private int foodY;      // 食物的y坐标

    private char direction = 'R'; // 初始方向：右
    private boolean isRunning = false;
    private Timer timer;
    private Random random;

    public SnakeGame() {
        random = new Random();
        setTitle("贪吃蛇游戏");
        setSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE + TILE_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        add(new GamePanel());
        addKeyListener(new KeyAdapter());

        startGame();
    }

    private void startGame() {
        snakeLength = 3;
        isRunning = true;
        direction = 'R';

        // 初始化蛇的位置
        for (int i = 0; i < snakeLength; i++) {
            x[i] = snakeLength - i - 1;
            y[i] = 0;
        }

        spawnFood();

        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(DELAY, e -> {
            if (isRunning) {
                move();
                checkCollision();
                checkFood();
            }
            repaint();
        });

        timer.start();
    }

    private void spawnFood() {
        foodX = random.nextInt(WIDTH);
        foodY = random.nextInt(HEIGHT);

        // 确保食物不会出现在蛇身上
        for (int i = 0; i < snakeLength; i++) {
            if (x[i] == foodX && y[i] == foodY) {
                spawnFood();
                return;
            }
        }
    }

    private void move() {
        // 移动蛇身
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // 移动蛇头
        switch (direction) {
            case 'U':
                y[0]--;
                break;
            case 'D':
                y[0]++;
                break;
            case 'L':
                x[0]--;
                break;
            case 'R':
                x[0]++;
                break;
        }
    }

    private void checkCollision() {
        // 检查是否撞墙
        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
            gameOver();
        }

        // 检查是否撞到自己
        for (int i = snakeLength; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                gameOver();
            }
        }
    }

    private void checkFood() {
        if (x[0] == foodX && y[0] == foodY) {
            snakeLength++;
            spawnFood();
        }
    }

    private void gameOver() {
        isRunning = false;
        timer.stop();

        int choice = JOptionPane.showOptionDialog(
                this,
                "游戏结束！你的得分: " + (snakeLength - 3) * 10 + "\n想再玩一次吗？",
                "游戏结束",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"再玩一次", "退出"},
                "再玩一次"
        );

        if (choice == 0) {
            startGame();
        } else {
            System.exit(0);
        }
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 绘制背景
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

            // 绘制蛇
            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN); // 蛇头
                } else {
                    g.setColor(new Color(45, 180, 0)); // 蛇身
                }
                g.fillRect(x[i] * TILE_SIZE, y[i] * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                // 蛇身边框
                g.setColor(Color.BLACK);
                g.drawRect(x[i] * TILE_SIZE, y[i] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            // 绘制食物
            g.setColor(Color.RED);
            g.fillOval(foodX * TILE_SIZE, foodY * TILE_SIZE, TILE_SIZE, TILE_SIZE);

            // 绘制分数
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("得分: " + (snakeLength - 3) * 10, 10, HEIGHT * TILE_SIZE + 20);

            // 游戏暂停提示
            if (!isRunning && timer != null && timer.isRunning()) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                String msg = "按空格键继续";
                int msgWidth = g.getFontMetrics().stringWidth(msg);
                g.drawString(msg, (WIDTH * TILE_SIZE - msgWidth) / 2, HEIGHT * TILE_SIZE / 2);
            }
        }
    }

    private class KeyAdapter extends java.awt.event.KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (direction != 'D') direction = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') direction = 'D';
                    break;
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') direction = 'R';
                    break;
                case KeyEvent.VK_SPACE:
                    if (isRunning) {
                        isRunning = false;
                    } else {
                        isRunning = true;
                    }
                    repaint();
                    break;
                case KeyEvent.VK_R:
                    if (!isRunning) startGame();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SnakeGame game = new SnakeGame();
            game.setVisible(true);
        });
    }
}
