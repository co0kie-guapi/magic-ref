package org.co0k1e.magicRef.executor.postProcessor;

import org.co0k1e.magicRef.pojo.FrameDataInfo;

/**
 * 后置处理器
 * @author co0k1e
 */
public interface ExecutorPostProcessor {

    /**
     * 执行后置处理器的操作
     * @param info
     * @return
     */
    FrameDataInfo doPostProcess(FrameDataInfo info);

}
