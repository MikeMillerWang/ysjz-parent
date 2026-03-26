package com.ysjz.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.ysjz.annotation.BigDecimalFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * BigDecimal 格式化序列化器
 * 结合注解 BigDecimalFormat 使用
 */
@Slf4j
public class FastjsonBigDecimalSerializer2 implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;

        // 处理null值的情况
        if (object == null) {
            out.writeNull();
            return;
        }

        BigDecimal value = (BigDecimal) object;

        // 获取字段配置
        FieldConfig config = getFieldConfig(object, fieldName != null ? fieldName.toString() : null);

        try {
            // 设置小数位数和舍入模式
            BigDecimal formatted = value.setScale(config.scale, config.roundingMode);

            if (config.padZero) {
                // 使用 DecimalFormat 确保补零
                StringBuilder pattern = new StringBuilder();
                pattern.append("0");
                if (config.scale > 0) {
                    pattern.append(".");
                    for (int i = 0; i < config.scale; i++) {
                        pattern.append("0");
                    }
                }
                DecimalFormat df = new DecimalFormat(pattern.toString());
                df.setRoundingMode(config.roundingMode);
                out.writeString(df.format(formatted));
            } else {
                // 不补零，直接输出
                out.writeString(formatted.stripTrailingZeros().toPlainString());
            }
        } catch (Exception e) {
            // 如果格式化失败，使用原始值
            out.writeString(value.toPlainString());
            log.error("FastJsonBigDecimalSerializer 格式化失败", e);
        }
    }

    /**
     * 获取字段配置（从注解）
     */
    private FieldConfig getFieldConfig(Object object, String fieldNameStr) {
        if (object == null || fieldNameStr == null) {
            // 返回默认配置
            return new FieldConfig(4, RoundingMode.HALF_UP, true);
        }

        // 通过反射获取字段注解
        try {
            Class<?> clazz = object.getClass();

            // 查找字段，包括父类中的字段
            Field field = findField(clazz, fieldNameStr);

            if (field != null) {
                field.setAccessible(true);

                // 获取注解
                BigDecimalFormat annotation = field.getAnnotation(BigDecimalFormat.class);
                if (annotation != null) {
                    // 创建配置对象
                    return new FieldConfig(
                            annotation.scale(),
                            annotation.roundingMode(),
                            annotation.padZero()
                    );
                }
            }
        } catch (Exception e) {
            // 记录日志或静默处理异常
            log.error("FastJsonBigDecimalSerializer 获取字段注解失败", e);
        }

        // 返回默认配置
        return new FieldConfig(4, RoundingMode.HALF_UP, true);
    }

    /**
     * 在类及其父类中查找字段
     */
    private Field findField(Class<?> clazz, String fieldName) {
        while (clazz != null && clazz != Object.class) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

    /**
     * 字段配置内部类
     */
    private static class FieldConfig {
        final int scale;
        final RoundingMode roundingMode;
        final boolean padZero;

        FieldConfig(int scale, RoundingMode roundingMode, boolean padZero) {
            this.scale = scale;
            this.roundingMode = roundingMode;
            this.padZero = padZero;
        }
    }
}