package christmas.validator;

import christmas.domain.contants.ExceptionMessage;

public interface Validator<T> {
    void validate(T param);
    default void throwException(String exceptionMessage) throws IllegalArgumentException {
        throw new IllegalArgumentException(ExceptionMessage.HEADER.getMessage() + exceptionMessage);
    }
}