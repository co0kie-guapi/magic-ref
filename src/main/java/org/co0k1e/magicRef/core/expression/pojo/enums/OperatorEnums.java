package org.co0k1e.magicRef.core.expression.pojo.enums;

import lombok.Getter;
import org.co0k1e.magicRef.IFunction.ThreeFunction;
import org.co0k1e.magicRef.core.expression.strategy.CalculateStrategy;

/**
 * 四则运算操作符
 * @author co0kie
 */
@Getter
public enum OperatorEnums {

    add(CalculateStrategy::add),
    off(CalculateStrategy::off),
    multiply(CalculateStrategy::multiply),
    divide(CalculateStrategy::divide) ;



    private ThreeFunction<CalculateStrategy,?,?,?> function;

    OperatorEnums(ThreeFunction<CalculateStrategy,?,?,?> function){
        this.function = function;
    }


}
