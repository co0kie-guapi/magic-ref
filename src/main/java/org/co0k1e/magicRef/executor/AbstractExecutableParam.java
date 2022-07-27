package org.co0k1e.magicRef.executor;

import java.util.List;

/**
 * 参数可执行的执行器
 * @author co0kie
 */
public abstract class AbstractExecutableParam extends AbstractExecutor {

    @Override
    protected void handleParams(List<Object> paramList, List<Class<?>> paramTypeList) {
        //循环处理
        for (int i = 0; i < paramList.size(); i++) {

        }
    }

    /**
     * 自定义转换参数 （例如判空替换空参之类的）
     * @param object
     * @param type
     * @return
     */
    protected abstract Object customTransferParam(Object object, Class<?> type);



}
