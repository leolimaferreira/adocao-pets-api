package io.github.leolimaferreira.adocao_pets_api.common.exception;

public class RegistroDuplicadoException extends RuntimeException{
    public RegistroDuplicadoException(String message) {
        super(message);
    }
}
