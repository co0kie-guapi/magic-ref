package org.co0k1e.magicRef.IFunction;

import java.io.Serializable;

/**
 * 支持序列化的 Condition
 */
@FunctionalInterface
public interface SCondition<T,B> extends Condition<T,B>, Serializable {
}
