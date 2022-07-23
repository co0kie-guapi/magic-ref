package org.co0k1e.magicRef.IFunction;

@FunctionalInterface
public interface Condition<T, B> {
    Boolean apply(T t, B b);
}
