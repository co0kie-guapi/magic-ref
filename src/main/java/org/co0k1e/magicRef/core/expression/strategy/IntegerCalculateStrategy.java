package org.co0k1e.magicRef.core.expression.strategy;

import java.math.BigDecimal;

/**
 * 整数类型计算策略实现类
 * @author co0kie
 */
public class IntegerCalculateStrategy extends AbstractCalculateStrategy{

    @Override
    public Integer addImpl(Object left, Object right) {
        return ((Integer)left) + ((Integer)right);
    }

    @Override
    public Integer offImpl(Object left, Object right) {
        return ((Integer)left) - ((Integer)right);
    }

    @Override
    public Integer multiplyImpl(Object left, Object right) {
        return ((Integer)left) * ((Integer)right);
    }

    @Override
    public Integer divideImpl(Object left, Object right) {
        return   ((Integer)left) / ((Integer)right);
    }

    @Override
    public Integer transvert(Object object) {
        if (object instanceof Integer){
            return (Integer) object;
        }else if (object instanceof String){
            return Integer.valueOf((String) object);
        }else if (object instanceof Double){
            return ((Double) object).intValue();
        }else if (object instanceof BigDecimal){
            return ((BigDecimal) object).intValue();
        }else{
            //todo 抛异常
        }
        return 0;
    }
}
