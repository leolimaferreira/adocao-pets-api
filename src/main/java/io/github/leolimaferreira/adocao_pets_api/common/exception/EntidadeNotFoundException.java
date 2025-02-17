package io.github.leolimaferreira.adocao_pets_api.common.exception;

public class EntidadeNotFoundException extends RuntimeException{
    public EntidadeNotFoundException(String message) {
        super(message);
    }
}
