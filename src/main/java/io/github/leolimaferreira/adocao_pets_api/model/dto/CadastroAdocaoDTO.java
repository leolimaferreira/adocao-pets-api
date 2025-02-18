package io.github.leolimaferreira.adocao_pets_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(name = "Adoção")
public record CadastroAdocaoDTO(
        UUID id,
        @NotNull(message = "campo obrigatório")
        UUID idPet,
        @NotNull(message = "campo obrigatório")
        UUID idAdotante
) {
}
