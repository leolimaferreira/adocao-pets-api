package io.github.leolimaferreira.adocao_pets_api.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CadastroAdocaoDTO(
        UUID id,
        @NotNull(message = "campo obrigatório")
        UUID idPet,
        @NotNull(message = "campo obrigatório")
        UUID idAutor
) {
}
