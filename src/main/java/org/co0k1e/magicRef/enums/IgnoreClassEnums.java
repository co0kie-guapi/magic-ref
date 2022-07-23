package org.co0k1e.magicRef.enums;

import lombok.Getter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
public enum IgnoreClassEnums {

    DATE_TYPE(Date.class);

    private Class<?> clazz;

    private static Set<Class> classSet = new HashSet<>();

    IgnoreClassEnums(Class<?> clazz){
        this.clazz = clazz;
    }

    static{
        IgnoreClassEnums[] values = IgnoreClassEnums.values();
        for (IgnoreClassEnums value : values) {
            classSet.add(value.getClazz());
        }
    }

    public static Boolean isInList(Class<?> clazz){
        return classSet.contains(clazz);
    }

}
