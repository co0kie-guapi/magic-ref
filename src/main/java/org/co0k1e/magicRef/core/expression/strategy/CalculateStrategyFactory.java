package org.co0k1e.magicRef.core.expression.strategy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 策略工厂
 * @author co0kie
 */
public class CalculateStrategyFactory {
    private static Map<Class<?>,CalculateStrategy> strategyMap = new HashMap();
    //初始化策略
    static {
        strategyMap.put(Integer.class,new IntegerCalculateStrategy());
        strategyMap.put(BigDecimal.class,new DecimalCalculateStrategy());
        strategyMap.put(String.class,new StringCalculateStrategy());
    }

    /**
     * 获取策略
     * @param clazz
     * @return
     */
    public static CalculateStrategy getStrategyInstance(Class<?> clazz){
        return strategyMap.get(clazz);
    }

    /**
     * 注册一个策略
     * @param clazz
     * @param calculate
     */
    public static void registryCalculateStrategy(Class<?> clazz,CalculateStrategy calculate){
        strategyMap.put(clazz,calculate);
    }


}
