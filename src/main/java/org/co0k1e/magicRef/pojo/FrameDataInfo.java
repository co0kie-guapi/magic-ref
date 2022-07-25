package org.co0k1e.magicRef.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 方法帧实体类
 * @author guapico0kie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FrameDataInfo {

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 运行成果
     */
    private Boolean successMark;

    /**
     * 返回结果
     */
    private Object returnValue;

    /**
     * 返回的类型
     */
    private Class<?> returnClazz;

    /**
     * 参数列表
     */
    private List<Object> paramList;

    /**
     * 参数类型列表
     */
    private List<Class<?>> paramTypeList;

    /**
     * 异常
     */
    private Exception exception;


}
