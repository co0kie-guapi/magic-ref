package org.co0k1e.magicRef.enums;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum DefaultValueEnums {
    BIG_DECIMAL(BigDecimal.class,BigDecimal.ZERO),
    STRING(String.class,""),
    INTEGER(Integer.class,0)

    ;

    private Class<?> clazz;
    private Object value;

    private static Map<Class, DefaultValueEnums> classMap = new HashMap<>();

    DefaultValueEnums(Class<?> clazz,Object value){
        this.clazz = clazz;
        this.value = value;
    }

    static{
        DefaultValueEnums[] values = DefaultValueEnums.values();
        for (DefaultValueEnums value : values) {
            classMap.put(value.getClazz(),value);
        }
    }

    public static DefaultValueEnums getEnumByClass(Class<?> clazz){
        return classMap.get(clazz);
    }



}
