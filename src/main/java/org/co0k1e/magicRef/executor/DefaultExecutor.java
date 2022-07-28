package org.co0k1e.magicRef.executor;

import org.co0k1e.magicRef.pojo.FrameDataInfo;



/**
 * 默认的执行器
 * @author co0kie
 */
public class DefaultExecutor extends AbstractUsualExecutor {
    @Override
    protected Object customTransferParam(Object object, Class<?> type) {
        return object;
    }


    @Override
    public FrameDataInfo doPostProcess(FrameDataInfo info) {
        return info;
    }
}
