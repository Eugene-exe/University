package com.botscrew.university.exception;

public interface CustomFn<A, T> {
    T apply(A a) throws Exception;
}