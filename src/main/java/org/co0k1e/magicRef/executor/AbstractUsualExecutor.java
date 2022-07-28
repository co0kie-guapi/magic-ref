package org.co0k1e.magicRef.executor;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 通用执行器
 * @author co0kie
 */
public abstract class AbstractUsualExecutor extends AbstractExecutableParam{
    @Override
    protected Object executeFunction(Serializable iFunction, List<Class<?>> paramsType, Object... params) {
        try{
            Method apply = iFunction.getClass().getMethod("apply", paramsType.toArray(new Class[0]));
            return apply.invoke("apply",params);
        } catch (NoSuchMethodException e) {
            System.out.println("参数数量不匹配");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
