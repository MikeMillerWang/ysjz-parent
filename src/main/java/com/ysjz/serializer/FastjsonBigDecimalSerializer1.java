package com.ysjz.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal, 四舍五入，保留小数点后四位，不足四位补0
 */
public class FastjsonBigDecimalSerializer1 implements ObjectSerializer {

    private static final int SCALE = 4;
    private static final RoundingMode ROUNDING = RoundingMode.HALF_UP;

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) {
        SerializeWriter out = serializer.out;

        if (object == null) {
            out.writeNull();
            return;
        }

        if (!(object instanceof BigDecimal)) {
            serializer.write(object);
            return;
        }

        BigDecimal value = ((BigDecimal) object).setScale(SCALE, ROUNDING);
        out.writeString(value.toPlainString()); // toPlainString 会保留 setScale 产生的补0效果
    }
}