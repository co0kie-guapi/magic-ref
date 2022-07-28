package org.co0k1e.magicRef.executor;



import org.co0k1e.magicRef.executor.postProcessor.ExecutorPostProcessor;
import org.co0k1e.magicRef.pojo.FrameDataInfo;
import org.co0k1e.magicRef.util.LambdaUtil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

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
//        Method IFunction = getMethod(function);
        //获取参数类型
        List<Class<?>> classes = Arrays.asList(LambdaUtil.getParamsTypes(function, 0));
        //处理参数
        handleParams(param,classes);
        //执行函数接口
        Object result = null;
        //获取返回值类型
        Class<?> returnType = LambdaUtil.getReturnType(function);
        FrameDataInfo.FrameDataInfoBuilder builder = FrameDataInfo.builder();

        builder.returnClazz(returnType)
                .methodName(LambdaUtil.getMethodName(function))
                .paramList(param)
                .paramTypeList(classes);

        try {
            result = executeFunction(function,classes ,param);
        }catch (Exception e){
            builder.exception(e)
                    .successMark(false);
            e.printStackTrace();
        }
        builder.returnValue(result);
        return doPostProcess(builder.build());
    }

    /**
     * 执行函数
     * @param iFunction
     * @param paramsType
     * @param params
     * @return
     */
    protected abstract Object executeFunction(Serializable iFunction,List<Class<?>>paramsType ,Object... params);





    /**
     * 参数处理器
     * @param paramList
     * @param
     */
    protected abstract void handleParams(List<Object> paramList,List<Class<?>> paramTypeList);







}
