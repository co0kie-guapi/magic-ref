package org.co0k1e.magicRef.core.expression;


import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.co0k1e.magicRef.IFunction.ThreeFunction;
import org.co0k1e.magicRef.core.expression.pojo.enums.OperatorEnums;
import org.co0k1e.magicRef.core.expression.strategy.CalculateStrategy;
import org.co0k1e.magicRef.core.expression.strategy.CalculateStrategyFactory;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 表达式解析
 * @author co0kie
 */
public class ExpressionHandle {

    private String expression;

    private List<Object> paramList;

    private Class<?> targetClass;

    static final Pattern regex = Pattern.compile("\\{([^}]*)\\}");

    /**
     * 运算符号优先级别
     */
    private static final Map<Character,Integer> priorityMap = new HashMap();


    /**
     * 符号
     */
    private List<String> symbolList = new ArrayList<String>();

    /**
     * 操作符栈
     */
    Stack<OperatorEnums> operatorStack = new Stack<>();

    /**
     *  初始化
     */
    static{
        priorityMap.put('(', 0);
        priorityMap.put('+', 1);
        priorityMap.put('-', 1);
        priorityMap.put('*', 2);
        priorityMap.put('/', 2);
    }

    /**
     * 获取一个handle
     * @param expression
     * @param paramList
     * @param targetClass
     * @return
     */
    public static ExpressionHandle getHandle(String expression,List<Object> paramList,Class<?> targetClass){
        return new ExpressionHandle(expression,paramList,targetClass);
    }


    private ExpressionHandle(String expression,List<Object> paramList,Class<?> targetClass){
        this.expression = expression;
        this.paramList = paramList;
        this.targetClass = targetClass;
    }

    /**
     * 计算表达式
     */
    public Object calculate(){
        List<String> polish = transferPolishExpr(this.expression);
        //获取策略
        Stack<Object> calculateStack = new Stack();
        for (String item : polish) {
            if (!OperatorEnums.isOperator(item)){
                calculateStack.push(item);
            }else{
                //是运算符
                OperatorEnums operatorEnums = OperatorEnums.obtainEnum(item);
                //根据类型获取计算策略
                CalculateStrategy strategyInstance = CalculateStrategyFactory.getStrategyInstance(this.targetClass);
                //弹栈需要计算的参数
                Object left = calculateStack.pop();
                Object right = calculateStack.pop();
                //解析参数如果是个符号变量后替换变量
                if (left instanceof String && ((String) left).startsWith("$")){
                    int indexFromSymbol = getIndexFromSymbol((String) left);
                    if (!CollectionUtil.isEmpty(paramList) && indexFromSymbol < paramList.size() ) {
                        left = paramList.get(indexFromSymbol);
                    }
                }

                if (right instanceof String && ((String) right).startsWith("$")){
                    int indexFromSymbol = getIndexFromSymbol((String) right);
                    if (!CollectionUtil.isEmpty(paramList) && indexFromSymbol < paramList.size() ) {
                        right = paramList.get(indexFromSymbol);
                    }
                }

                ThreeFunction<CalculateStrategy, Object, Object,Object> function = operatorEnums.getFunction();
                Object apply = function.apply(strategyInstance, left, right);
                calculateStack.push(apply);
            }
        }
        return calculateStack.pop();
    }


    /**
     * 转换为逆波兰表达式
     * 参考： https://gist.github.com/cserspring/0b8a1fb107cf7e81d76c11da508d85de
     * @param originExpr
     * @return
     */
    private  List<String> transferPolishExpr(String originExpr){
        Stack<Character> operatorStack = new Stack();
        List<String> polishExpr = new ArrayList<>();
        //将表达式转换为计算式
        Matcher matcher = regex.matcher(originExpr);
        if (matcher.find()){
            String group = matcher.group(1);
            int end = 0;
            while (end < group.length()) {
                int start = end;
                char charAt = group.charAt(end++);
                if (charAt == '('){
                    operatorStack.push('(');
                }
                else if(priorityMap.containsKey(charAt) && charAt != '(' ){
                   while (!operatorStack.isEmpty() && priorityMap.get(charAt) <= priorityMap.get(operatorStack.peek())){
                       polishExpr.add(String.valueOf(operatorStack.pop()) );
                   }
                   operatorStack.push(charAt);
                }
                else if (charAt == ')') {
                    while (operatorStack.peek() != '('){
                        polishExpr.add(String.valueOf(operatorStack.pop()));
                    }
                    operatorStack.pop();
                }
                else if (charAt == '$'){
                    //循环
                    while (end < group.length() && !priorityMap.containsKey( group.charAt(end)) &&  group.charAt(end)!= ' ' ){
                        ++end;
                    }
                    String substring = group.substring(start, end);
                    polishExpr.add(String.valueOf(substring));
                }
                else if (Character.isDigit(charAt)){
                    while (end < group.length() && (Character.isDigit(group.charAt(end)) || group.charAt(end) == '.' )){
                        ++end;
                    }
                    polishExpr.add(group.substring(start, end));
                }
            }
        }else{
            //表达式不合规
        }
        while (!operatorStack.isEmpty()) {
            polishExpr.add(String.valueOf(operatorStack.pop()));
        }
        return polishExpr;


    }

    /**
     * 解析符号获取参数索引值
     * @param symbol
     * @return
     */
    private  Integer getIndexFromSymbol(String symbol){
        String substring = symbol.substring(1);
        if (NumberUtils.isDigits(substring)) {
            Integer index = Integer.valueOf(substring);
            return index;
        }else{
            //todo 抛参数异常
            throw new InvalidParameterException();
        }

    }






}
