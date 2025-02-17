package io.github.leolimaferreira.adocao_pets_api.model.dto;

public record ErroCampoDTO (
        String campo,
        String erro
) {
}
