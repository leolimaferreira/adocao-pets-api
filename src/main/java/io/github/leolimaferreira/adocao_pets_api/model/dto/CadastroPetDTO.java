package io.github.leolimaferreira.adocao_pets_api.model.dto;

import io.github.leolimaferreira.adocao_pets_api.model.StatusPet;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Schema(name = "Pet")
public record CadastroPetDTO(
        @NotBlank(message = "campo obrigatório")
        @Size(max = 100)
        String nome,
        @NotBlank(message = "campo obrigatório")
        @Size(max = 50)
        String especie,
        Integer idade,
        StatusPet status,
        String descricao
) {
}
