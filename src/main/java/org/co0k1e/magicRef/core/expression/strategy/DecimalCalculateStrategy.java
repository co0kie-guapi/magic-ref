package org.co0k1e.magicRef.core.expression.strategy;

import org.co0k1e.magicRef.MagicRef;

import java.math.BigDecimal;

/**
 * bigDecimal类型运算策略类
 * @author co0kie
 */
public class DecimalCalculateStrategy extends AbstractCalculateStrategy{
    @Override
    public BigDecimal addImpl(Object left, Object right) {
        return ((BigDecimal)left).add((BigDecimal) right);
    }

    @Override
    public BigDecimal offImpl(Object left, Object right) {
        return ((BigDecimal)left).subtract((BigDecimal) right);
    }

    @Override
    public BigDecimal multiplyImpl(Object left, Object right) {
        return ((BigDecimal)left).multiply((BigDecimal) right);
    }

    @Override
    public BigDecimal divideImpl(Object left, Object right) {
        return ((BigDecimal)left).divide((BigDecimal) right,4);
    }

    @Override
    public BigDecimal transvert(Object object) {
        if (object == null){
            return (BigDecimal) MagicRef.inBox(null).unWrap(BigDecimal.class);
        }

        if(object instanceof BigDecimal){
            return (BigDecimal) object;
        }

        if(object instanceof String){
            return new BigDecimal((String)object);
        }

        return (BigDecimal) MagicRef.inBox(null).unWrap(BigDecimal.class);
    }
}
