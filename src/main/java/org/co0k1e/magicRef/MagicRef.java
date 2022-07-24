package org.co0k1e.magicRef;


import lombok.SneakyThrows;
import org.co0k1e.magicRef.IFunction.SCondition;
import org.co0k1e.magicRef.IFunction.SFunction;
import org.co0k1e.magicRef.IFunction.SInvoker;
import org.co0k1e.magicRef.IFunction.SThreeFunction;
import org.co0k1e.magicRef.enums.DefaultValueEnums;
import org.co0k1e.magicRef.enums.IgnoreClassEnums;
import org.co0k1e.magicRef.util.LambdaUtil;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 魔术指针 （待完善）
 * @author yanghx
 */
public class MagicRef<T> {

    private final T object;

    /**
     * 用作包装对象
     * @param value
     * @param <T>
     * @return
     */
    public static <T> MagicRef<T> inBox(T value){
        return new MagicRef<>(value);
    }

    /**
     * 当对象为空的时候返回默认值
     * @return
     */
    @SneakyThrows
    public T unWrap(Class clazz){
        if (this.object == null){
            Object o = handleNullPtrByClass(clazz, null);
            return (T) o;
        }else{
            return this.object;
        }
    }


    /**
     * 直接拆盒,不做默认值处理
     * @return
     */
    public T unBox(){
        return this.object;
    }


    /**
     * 调用方法 无参有返回值
     * @param method
     */
    public MagicRef<?> invoke(SFunction<T,?> method){
        T t = unBox();
        t = handleNullPtrByMethod(method,t);
        Object apply = method.apply(t);
        return MagicRef.inBox(apply);
    }

    /**
     * 调用方法 单参 有返回值
     * @param method
     * @param object
     * @return
     */
    @SneakyThrows
    public MagicRef<?> invoke(SInvoker<T,?,?> method, Object object){
        T t = unBox();
        //非空判断处理空指针问题
        t = handleNullPtrByMethod(method,t);
        //noinspection unchecked
        Method apply = method.getClass().getMethod("apply", Object.class, Object.class);
        apply.setAccessible(true);
        Object invoke = apply.invoke(method, t, object);
        return MagicRef.inBox(invoke) ;
    }

    /**
     * 调用方法 双参 有返回值
     * @param method
     * @param var1
     * @param var2
     * @return
     */
    @SneakyThrows
    public MagicRef<?> invoke(SThreeFunction<T,?,?,?> method, Object var1, Object var2){
        T t = unBox();
        t = handleNullPtrByMethod(method,t);
        Method apply = method.getClass().getMethod("apply", Object.class, Object.class,Object.class);
        apply.setAccessible(true);
        Object invoke = apply.invoke(method, t, var1,var2);
        return MagicRef.inBox(invoke) ;
    }

    /**
     * 调用条件式
     * @param
     * @return
     */
    @SneakyThrows
    public Boolean condition(SCondition<T,?> condition, Object var1){
        T t = unBox();
        t = handleNullPtrByMethod(condition,t);
        Method apply = condition.getClass().getMethod("apply", Object.class, Object.class);
        apply.setAccessible(true);
        return (Boolean) apply.invoke(condition, t, var1);
    }






    public boolean isEmpty(){
        return this.object == null;
    }



    private MagicRef(T object) {
        this.object = object;
    }

    /**
     * 处理空指针如果这个类本身封装的对象为空
     *
     * @param method
     * @param  t
     * @return
     */
    @SneakyThrows
    private T handleNullPtrByMethod(Serializable method, T t){
        if (t == null){
            //通过方法获取类型
            Class<?> ownerClass = LambdaUtil.getOwnerClass(method);
            DefaultValueEnums enums = DefaultValueEnums.getEnumByClass(ownerClass);
            if (enums != null){
                //noinspection unchecked
                return (T) enums.getValue();
            }
            //不在配置的默认值里查找是否在忽视的列表中
            if (IgnoreClassEnums.isInList(ownerClass)){
                throw new NullPointerException(ownerClass.getName() + "在忽略列表，无法生成默认值");
            }
            //通过反射实例化（须有无参构造，否则会抛异常）
            Constructor<?> constructor = ownerClass.getDeclaredConstructor();
            //noinspection unchecked
            return (T) constructor.newInstance();

        }else{
            return t;
        }



    }

    @SneakyThrows
    private Object handleNullPtrByClass(Class<?> clazz,Object object){
        if (object == null){
            DefaultValueEnums enums = DefaultValueEnums.getEnumByClass(clazz);
            if (enums != null){
                return  enums.getValue();
            }
            if (IgnoreClassEnums.isInList(clazz)){
                throw new NullPointerException(clazz.getName() + "在忽略列表，无法生成默认值");
            }
            return clazz.getDeclaredConstructor().newInstance();
        }
        return object;

    }








}
