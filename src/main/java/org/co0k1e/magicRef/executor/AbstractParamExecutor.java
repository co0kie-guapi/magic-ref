package org.co0k1e.magicRef.executor;

import org.co0k1e.magicRef.pojo.FrameDataInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 不具备执行参数执行器的子类
 * 防止同个Executor又被当作执行者又被当作参数导致死循环
 * @author co0kie
 */
public abstract class AbstractParamExecutor extends AbstractExecutor{

    @Override
    protected void handleParams(List<Object> paramList, List<Class<?>> paramTypeList) {

    }

}
