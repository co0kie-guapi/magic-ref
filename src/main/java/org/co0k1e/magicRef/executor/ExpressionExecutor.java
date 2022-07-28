package org.co0k1e.magicRef.executor;

import org.co0k1e.magicRef.pojo.FrameDataInfo;

/**
 * 支持表达式解析的执行类
 * @author co0kie
 */
public class ExpressionExecutor extends AutoFullParamExecutor{

    /**
     * 表达式最后输出的类型
     */
    private Class<?> targetClass;

    @Override
    public FrameDataInfo doPostProcess(FrameDataInfo info) {

        return super.doPostProcess(info);
    }
}
