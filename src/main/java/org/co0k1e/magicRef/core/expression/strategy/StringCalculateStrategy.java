package org.co0k1e.magicRef.core.expression.strategy;

import org.co0k1e.magicRef.MagicRef;

/**
 * 字符串策略类
 * @author co0kie
 */
public class StringCalculateStrategy extends AbstractCalculateStrategy {
    @Override
    public Object addImpl(Object left, Object right) {
        return (String) left + (String)right ;
    }

    @Override
    public Object offImpl(Object left, Object right) {
        throw new UnsupportedOperationException("String type cannot used in subtract operation");
    }

    @Override
    public Object multiplyImpl(Object left, Object right) {
        StringBuilder builder = new StringBuilder();
        Integer integer = 0;
        if (isNumb((String) right)){
            integer = Integer.valueOf((String) right);
        } else if(isNumb((String) left)){
            integer = Integer.valueOf((String) left);
        }else{
            throw new UnsupportedOperationException("String type cannot used in multiply operation where not have a Integer value");
        }
        for (int i = 0; i < integer; i++) {
            builder.append(left);
        }
        return builder.toString();
    }

    @Override
    public Object divideImpl(Object left, Object right) {
        throw new UnsupportedOperationException("String type cannot used in divide operation");
    }

    @Override
    public Object transvert(Object object) {
        if (object == null){
            //获取String默认值
            MagicRef<String> objectMagicRef = MagicRef.inBox((String) null);
            return objectMagicRef.unWrap(String.class);
        }
        if (object instanceof String){
            return object;
        }else{
            object.toString();
        }
        return null;
    }

    private static Boolean isNumb(String var){
        for (int i = 0; i < var.length(); i++) {
            char c = var.charAt(i);
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }



}
