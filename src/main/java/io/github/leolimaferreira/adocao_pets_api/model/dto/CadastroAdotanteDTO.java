package io.github.leolimaferreira.adocao_pets_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Adotante")
public record CadastroAdotanteDTO(
        UUID id,
        @NotBlank(message = "campo obrigatório")
        @Size(max = 100, message = "campo fora do tamanho padrão")
        String nome,
        @NotBlank(message = "campo obrigatório")
        @Size(max = 100, message = "campo fora do tamanho padrão")
        String email,
        @Size(max = 20, message = "campo fora do tamanho padrão")
        String telefone,
        @Size(max = 255, message = "campo fora do tamanho padrão")
        String endereco,
        LocalDate dataNascimento
) {
}
