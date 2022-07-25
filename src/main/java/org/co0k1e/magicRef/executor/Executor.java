package org.co0k1e.magicRef.executor;

import org.co0k1e.magicRef.pojo.FrameDataInfo;

import java.io.Serializable;

/**
 * 执行器
 * @author co0kie
 */
public interface Executor extends Serializable {

    /**
     * 执行代码
     * @return
     */
    FrameDataInfo doExecute();

}
