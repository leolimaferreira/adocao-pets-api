package io.github.leolimaferreira.adocao_pets_api.model.dto;

import io.github.leolimaferreira.adocao_pets_api.model.StatusPet;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "Resultado Pesquisa de Pet")
public record ResultadoPesquisaPetDTO(
        UUID id,
        String nome,
        String especie,
        Integer idade,
        StatusPet status,
        String descricao,
        CadastroAdotanteDTO adotante
) {
}
