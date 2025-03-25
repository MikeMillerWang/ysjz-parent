package com.ysjz.service.impl;

import com.ysjz.service.Expression;

// 非终结符表达式类，表示加法操作
public class AddExpression implements Expression {
    private Expression left;
    private Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }

    public static void main(String[] args) {
        // 构建表达式：1 + 2 + 3
        Expression number1 = new NumberExpression(1);
        Expression number2 = new NumberExpression(2);
        Expression number3 = new NumberExpression(3);

        // 创建加法表达式树
        Expression addition1 = new AddExpression(number1, number2);
        Expression addition2 = new AddExpression(addition1, number3);

        // 计算结果
        int result = addition2.interpret();
        System.out.println("Result: " + result);  // 输出: Result: 6
    }
}

