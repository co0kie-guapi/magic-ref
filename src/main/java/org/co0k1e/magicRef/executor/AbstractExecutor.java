package org.co0k1e.magicRef.executor;


import cn.hutool.core.collection.CollectionUtil;
import org.co0k1e.magicRef.executor.postProcessor.ExecutorPostProcessor;
import org.co0k1e.magicRef.pojo.FrameDataInfo;
import org.co0k1e.magicRef.util.LambdaUtil;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 执行器抽象类
 * @author co0kie
 */
public abstract class AbstractExecutor implements Executor, ExecutorPostProcessor {
    /**
     * 执行方法
     */
    private Serializable function;
    /**
     * 参数
     */
    private List<Object> param;
    /**
     * 主要执行方法，不推荐后续再重写
     * @return
     */
    @Override
    public FrameDataInfo doExecute() {
        Method IFunction = getMethod(function);
        //获取参数
        List<Class<?>> classes = Arrays.asList(LambdaUtil.getParamsTypes(function, 0));
        //处理参数
        handleParams(param,classes);
        //执行函数接口
        Object result = null;
        //获取返回值类型
        Class<?> returnType = LambdaUtil.getReturnType(function);
        FrameDataInfo.FrameDataInfoBuilder builder = FrameDataInfo.builder();
        builder.returnClazz(returnType);

        try {
            result = executeFunction(IFunction, param);
        }catch (Exception e){
            builder.exception(e)
                    .successMark(false);
        }
        builder.returnValue(result);




        return doPostProcess(builder.build());
    }


    protected abstract Object executeFunction(Method IFunction,Object... params);

    /**
     * 通过函数接口获取方法
     * @param function 可序列化的函数式接口
     * @return
     */
    protected abstract Method getMethod(Serializable function);





    /**
     * 参数处理器
     * @param paramList
     * @param
     */
    protected abstract void handleParams(List<Object> paramList,List<Class<?>> paramTypeList);







}
