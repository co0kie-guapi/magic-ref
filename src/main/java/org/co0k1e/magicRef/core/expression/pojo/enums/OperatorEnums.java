package org.co0k1e.magicRef.core.expression.pojo.enums;

import lombok.Getter;
import org.co0k1e.magicRef.IFunction.ThreeFunction;
import org.co0k1e.magicRef.core.expression.strategy.CalculateStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 四则运算操作符
 * @author co0kie
 */
@Getter
public enum OperatorEnums {

    add(CalculateStrategy::add,"+"),
    off(CalculateStrategy::off,"-"),
    multiply(CalculateStrategy::multiply,"*"),
    divide(CalculateStrategy::divide,"/") ;

    private static Map<String,OperatorEnums> map= new HashMap<>();

    static {
        for (OperatorEnums value : OperatorEnums.values()) {
            map.put(value.getExpr(),value);
        }
    }



    private ThreeFunction<CalculateStrategy,Object,Object,Object> function;
    private String expr;

    OperatorEnums(ThreeFunction<CalculateStrategy,Object,Object,Object> function,String expr){
        this.function = function;
        this.expr = expr;
    }

    public static Boolean isOperator(String expr){
        return map.containsKey(expr);
    }

    public static OperatorEnums obtainEnum(String expr){
        return map.get(expr);
    }




}
