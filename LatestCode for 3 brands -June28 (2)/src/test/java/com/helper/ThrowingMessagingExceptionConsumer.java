package com.helper;

import javax.mail.MessagingException;

@FunctionalInterface
public interface ThrowingMessagingExceptionConsumer<T, E extends Exception> {
    void accept(T t) throws E, MessagingException;
}

