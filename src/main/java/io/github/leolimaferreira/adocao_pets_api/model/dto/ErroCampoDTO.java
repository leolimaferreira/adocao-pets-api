package io.github.leolimaferreira.adocao_pets_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Erro Campo")
public record ErroCampoDTO (
        String campo,
        String erro
) {
}
