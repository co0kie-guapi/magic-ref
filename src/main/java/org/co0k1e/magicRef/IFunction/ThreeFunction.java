package org.co0k1e.magicRef.IFunction;

@FunctionalInterface
public interface ThreeFunction<T, B,K,R> {
    R apply(T t, B b, K k);
}
