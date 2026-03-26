package com.ysjz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.RoundingMode;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BigDecimalFormat {

    /**
     * 小数位数，默认4位
     */
    int scale() default 4;

    /**
     * 舍入模式，默认四舍五入
     */
    RoundingMode roundingMode() default RoundingMode.HALF_UP;

    /**
     * 是否补零，默认补零
     */
    boolean padZero() default true;
}
