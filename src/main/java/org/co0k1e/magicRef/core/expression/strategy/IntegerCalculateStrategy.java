package org.co0k1e.magicRef.core.expression.strategy;

import java.math.BigDecimal;

/**
 * 整数类型计算策略实现类
 * @author co0kie
 */
public class IntegerCalculateStrategy implements CalculateStrategy,Transverter{

    @Override
    public Object add(Object left, Object right) {
        Integer lInt = (Integer) transvert(left);
        Integer RInt = (Integer) transvert(right);
        return lInt + RInt;
    }

    @Override
    public Object off(Object left, Object right) {
        Integer lInt = (Integer) transvert(left);
        Integer RInt = (Integer) transvert(right);

        return lInt - RInt;
    }

    @Override
    public Object multiply(Object left, Object right) {
        Integer lInt = (Integer) transvert(left);
        Integer RInt = (Integer) transvert(right);

        return lInt * RInt;
    }

    @Override
    public Object divide(Object left, Object right) {
        return null;
    }

    @Override
    public Object transvert(Object object) {
        if (object instanceof Integer){
            return object;
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
