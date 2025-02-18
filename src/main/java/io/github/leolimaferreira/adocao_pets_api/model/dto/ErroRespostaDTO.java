package io.github.leolimaferreira.adocao_pets_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.util.List;

@Schema(name = "Erro Resposta")
public record ErroRespostaDTO(
        int status,
        String mensagem,
        List<ErroCampoDTO> erros
) {
    public static ErroRespostaDTO respostaPadrao(String mensagem) {
        return new ErroRespostaDTO(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static ErroRespostaDTO conflito(String mensagem) {
        return new ErroRespostaDTO(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
