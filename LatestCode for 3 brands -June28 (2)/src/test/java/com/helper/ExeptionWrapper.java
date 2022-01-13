package com.helper;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

public class ExeptionWrapper {

    public static <T> Consumer<T> throwingMsgExeptionConsumerWrapper(
            ThrowingMessagingExceptionConsumer<T, MessagingException> throwingConsumer) {

        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (MessagingException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    public static <T, R, E extends Exception>
    Function<T, R> throwingIOExeptionWrapper(FunctionWithException<T, R, E> fe) {
        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
