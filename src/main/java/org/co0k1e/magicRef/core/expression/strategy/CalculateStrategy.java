package org.co0k1e.magicRef.core.expression.strategy;

/**
 * 表达式计算策略接口
 * @author co0kie
 */
public interface CalculateStrategy {

    /**
     * 相加
     * @param left
     * @param right
     * @return
     */
    Object add(Object left,Object right);


    /**
     * 减
     * @param left
     * @param right
     * @return
     */
    Object off(Object left,Object right);



    /**
     * 乘
     * @param left
     * @param right
     * @return
     */
    Object multiply(Object left,Object right);

    /**
     * 除
     * @param left
     * @param right
     * @return
     */
    Object divide(Object left,Object right);




}
