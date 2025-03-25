package com.ysjz.service;

import com.ysjz.service.impl.NumberFactoryImpl;

import java.math.BigDecimal;

public interface NumberFactory {

    static Number parse(String s) {
        return new BigDecimal(s);
    }

   /* static NumberFactory getFactory() {
        return imp1;
    }

    NumberFactory imp1 = new NumberFactoryImpl();*/
}
