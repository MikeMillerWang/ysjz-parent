package com.ysjz.service.impl;

import com.ysjz.service.Expression;

// 终结符表达式类，表示数字
public class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return this.number;
    }
}

