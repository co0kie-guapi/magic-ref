package org.co0k1e.magicRef.IFunction;
import java.io.Serializable;
import java.util.function.BiFunction;

@FunctionalInterface
public interface SInvoker<T, B,R> extends BiFunction<T, B,R>, Serializable {

}
