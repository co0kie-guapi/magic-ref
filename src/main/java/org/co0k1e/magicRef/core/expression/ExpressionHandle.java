package org.co0k1e.magicRef.core.expression;

import org.co0k1e.magicRef.core.expression.pojo.enums.OperatorEnums;

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
     * 先写死做个测试
     */
    public Object calculate(){
        List<String> polish = transferPolishExpr(this.expression);
        Stack<String> calculateStack = new Stack();
        for (String item : polish) {
            if (!OperatorEnums.isOperator(item)){
                calculateStack.push(item);
            }else{
                //发现运算符
            }
        }
        return null;
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




}
