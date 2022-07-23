package org.co0k1e.magicRef.util;

import lombok.SneakyThrows;
import org.co0k1e.magicRef.core.SerializedLambda;
import org.co0k1e.magicRef.core.SetAccessibleAction;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

/**
 * lambda工具类
 * @author yanghx
 */
public class LambdaUtil {


    /**
     * 获取表达式的返回值类型
     * @param lambda
     * @return
     */
    @SneakyThrows
    public static Class<?> getReturnType(Serializable lambda){
        String methodType = getInstantiatedType(lambda);
        String returnClassName = methodType.substring(methodType.indexOf(')') + 2,methodType.length()-1).replace('/', '.');
        return Class.forName(returnClassName);
    }

    /**
     * 获取参数类型列表
     * @param lambda
     * @param startAt
     * @return
     */
    @SneakyThrows
    public static Class<?>[] getParamsTypes(Serializable lambda,Integer startAt){
        String methodType = getInstantiatedType(lambda);
        int of = methodType.indexOf(")");
        String charSequence = (String) methodType.subSequence(1, of);
        String[] split = charSequence.split(";");
        List<Class> classes = new ArrayList<>();
        for (int i = startAt; i < split.length; i++) {
            //将native class name 转成正常的class
            String nativeClassName = split[i];
            String className = nativeClassName.substring(1).replace('/', '.');
            classes.add(Class.forName(className));
        }

        return (Class<?>[]) classes.toArray();
    }

    /**
     * 获取方法所有对象的类
     * @return
     */
    @SneakyThrows
    public static Class<?> getOwnerClass(Serializable lambda){
        String methodType = getInstantiatedType(lambda);
        int of = methodType.indexOf(")");
        String charSequence = (String) methodType.subSequence(1, of);
        String[] split = charSequence.split(";");
        String nativeClassName = split[0];
        String className = nativeClassName.substring(1).replace('/', '.');
        return Class.forName(className);
    }


    /**
     * 获取方法签名的jni字符串
     * @param lambda
     * @return
     */
    @SneakyThrows
    private static String getInstantiatedType(Serializable lambda){
        Method method = lambda.getClass().getDeclaredMethod("writeReplace");
        java.lang.invoke.SerializedLambda returnTypeByReflect = getReturnTypeByReflect(method,lambda);
        if (returnTypeByReflect != null){
            return returnTypeByReflect.getInstantiatedMethodType();
        }else{
            SerializedLambda resolve = SerializedLambda.resolve(lambda);
            return resolve.getInstantiatedMethodType();
        }

    }




    @SneakyThrows
    private static java.lang.invoke.SerializedLambda getReturnTypeByReflect(Method method,Serializable lambda){
        if (method == null){
            return null;
        }
        Method privileged = AccessController.doPrivileged(new SetAccessibleAction<>(method));
        return ( java.lang.invoke.SerializedLambda) privileged.invoke(lambda);
    }


}
