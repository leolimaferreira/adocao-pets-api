package io.github.leolimaferreira.adocao_pets_api.common.exception;

public class OperacaoNaoPermitidaException extends RuntimeException{
    public OperacaoNaoPermitidaException(String message) {
        super(message);
    }
}