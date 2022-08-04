package org.co0k1e.magicRef.core.expression.strategy;

import java.math.BigDecimal;

/**
 * 计算策略抽象类
 * @author co0kie
 */
public abstract class AbstractCalculateStrategy implements CalculateStrategy,Transverter{
    @Override
    public Object add(Object left, Object right) {
        Object l = transvert(left);
        Object r = transvert(right);
        return addImpl(l,r);
    }

    @Override
    public Object off(Object left, Object right) {
        Object l = transvert(left);
        Object r = transvert(right);
        return offImpl(l,r);
    }

    @Override
    public Object multiply(Object left, Object right) {
        Object l = transvert(left);
        Object r = transvert(right);
        return multiplyImpl(l,r);
    }

    @Override
    public Object divide(Object left, Object right) {
        Object l = transvert(left);
        Object r = transvert(right);
        return divideImpl(l,r);
    }

    /**
     * 添加的实现方法
     * @param left
     * @param right
     * @return
     */
    public abstract Object addImpl(Object left, Object right);


    /**
     * 添加的实现方法
     * @param left
     * @param right
     * @return
     */
    public abstract Object offImpl(Object left, Object right);


    /**
     * 乘的实现方法
     * @param left
     * @param right
     * @return
     */
    public abstract Object multiplyImpl(Object left, Object right);


    /**
     * 除的实现方法
     * @param left
     * @param right
     * @return
     */
    public abstract Object divideImpl(Object left, Object right);


}
