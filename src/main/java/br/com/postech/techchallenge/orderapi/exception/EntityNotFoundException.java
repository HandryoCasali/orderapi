package br.com.postech.techchallenge.orderapi.exception;

public class EntityNotFoundException extends BaseException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
