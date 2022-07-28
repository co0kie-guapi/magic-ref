package org.co0k1e.magicRef.core.expression;

import java.util.List;

/**
 * 表达式解析
 * @author co0kie
 */
public class ExpressionHandle {

    private String expression;

    private List<Object> paramList;

    private Class<?> targetClass;


    public static ExpressionHandle getHandle(String expression,List<Object> paramList,Class<?> targetClass){
        return new ExpressionHandle(expression,paramList,targetClass);
    }


    private ExpressionHandle(String expression,List<Object> paramList,Class<?> targetClass){
        this.expression = expression;
        this.paramList = paramList;
        this.targetClass = targetClass;
    }


}
