package com.helper;

import java.io.IOException;

@FunctionalInterface
public interface FunctionWithException<T, R, E extends Exception> {

    R apply(T t) throws E;
}

