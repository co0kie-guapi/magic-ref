package org.co0k1e.magicRef.executor;

import org.co0k1e.magicRef.MagicRef;
import org.co0k1e.magicRef.pojo.FrameDataInfo;

/**
 * 自动装填执行类
 * @author co0kie
 */
public class AutoFullParamExecutor extends AbstractUsualExecutor{
    @Override
    protected Object customTransferParam(Object object, Class<?> type) {
        MagicRef<Object> magicRef = MagicRef.inBox(object);
        return magicRef.unWrap(type);
    }

    @Override
    public FrameDataInfo doPostProcess(FrameDataInfo info) {
        return info;
    }
}
