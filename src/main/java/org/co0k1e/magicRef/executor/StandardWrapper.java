package org.co0k1e.magicRef.executor;

import org.co0k1e.magicRef.IFunction.SFunction;
import org.co0k1e.magicRef.IFunction.SInvoker;
import org.co0k1e.magicRef.IFunction.SThreeFunction;

/**
 * 标准包装器
 * @author co0k1e
 */
public interface StandardWrapper {

    /**
     * 自动匹配provider的包装器
     * @param invoker
     * @param var1
     * @return
     */
    Object andThen(SInvoker<?,?,?> invoker,Object var1);

    /**
     * 自动匹配provider的包装器
     * @param function
     * @param var1
     * @param var2
     * @return
     */
    Object andThen(SThreeFunction<?,?,?,?> function, Object var1, Object var2);


    /**
     * 自动匹配provider的包装器
     * @param function
     * @return
     */
    Object andThen(SFunction<?,?> function);

    /**
     * 条件处理的执行器建造
     * @param condition
     * @param invoker
     * @param var1
     * @return
     */
    Object andThenWithCondition(Boolean condition,SInvoker<?,?,?> invoker,Object var1);


    /**
     * 条件处理的的执行器建造
     * @param condition
     * @param function
     * @param var1
     * @param var2
     * @return
     */
    Object andThenWithCondition(Boolean condition,SThreeFunction<?,?,?,?> function,Object var1,Object var2);


    /**
     * 条件处理的执行器建造
     * @param condition
     * @param function
     * @return
     */
    Object andThenWithCondition(Boolean condition,SFunction<?,?> function);



    /**
     * 条件处理的执行器建造
     * @param condition
     * @param invoker
     * @param var1
     * @return
     */
    Object andThenWithCondition(Executor condition,SInvoker<?,?,?> invoker,Object var1);



    /**
     * 条件处理的的执行器建造
     * @param condition
     * @param function
     * @param var1
     * @param var2
     * @return
     */
    Object andThenWithCondition(Executor condition,SThreeFunction<?,?,?,?> function,Object var1,Object var2);


    /**
     * 条件处理的的执行器建造
     * @param condition
     * @param function

     * @return
     */
    Object andThenWithCondition(Executor condition,SFunction<?,?> function);




}
