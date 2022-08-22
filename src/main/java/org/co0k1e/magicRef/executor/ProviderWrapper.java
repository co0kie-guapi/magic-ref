package org.co0k1e.magicRef.executor;

import org.co0k1e.magicRef.IFunction.SFunction;
import org.co0k1e.magicRef.IFunction.SInvoker;
import org.co0k1e.magicRef.IFunction.SThreeFunction;

public interface ProviderWrapper {


    /**
     * 自动匹配provider的包装器
     * @param invoker
     * @param var1
     * @return
     */
    Object then(SInvoker<?,?,?> invoker,Object provider ,Object var1);

    /**
     * 自动匹配provider的包装器
     * @param function
     * @param provider
     * @param var1
     * @param var2
     * @return
     */
    Object then(SThreeFunction<?,?,?,?> function, Object provider , Object var1, Object var2);


    /**
     * 自动匹配provider的包装器
     * @param function
     * @param provider
     * @return
     */
    Object then(SFunction<?,?> function,Object provider);

    /**
     * 条件处理的执行器建造
     * @param condition
     * @param invoker
     * @param var1
     * @return
     */
    Object thenWithCondition(Boolean condition,SInvoker<?,?,?> invoker,Object var1);


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
