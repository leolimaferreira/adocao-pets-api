package io.github.leolimaferreira.adocao_pets_api.model.dto;

import io.github.leolimaferreira.adocao_pets_api.model.StatusPet;

import java.util.UUID;

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
