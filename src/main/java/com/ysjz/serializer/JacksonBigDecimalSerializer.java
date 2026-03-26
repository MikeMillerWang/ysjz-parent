package com.ysjz.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * jackson 序列化 BigDecimal
 */
public class JacksonBigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (value != null) {
            // 保留4位小数，四舍五入
            BigDecimal number = value.setScale(4, RoundingMode.HALF_UP);
            String formatted = String.format("%.4f", number);
            jsonGenerator.writeString(formatted);
        } else {
            jsonGenerator.writeNumber(value);
        }
    }

    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal("1.23");
        BigDecimal number = b1.setScale(4, RoundingMode.HALF_UP);
        System.out.println(b1);
        System.out.println(number);
    }
}
