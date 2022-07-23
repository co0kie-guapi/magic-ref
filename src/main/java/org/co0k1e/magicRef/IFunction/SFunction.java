package org.co0k1e.magicRef.IFunction;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 支持序列化的 Function
 */
@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}
