package io.github.leolimaferreira.adocao_pets_api.controller;

import io.github.leolimaferreira.adocao_pets_api.model.Adotante;
import io.github.leolimaferreira.adocao_pets_api.model.dto.CadastroAdotanteDTO;
import io.github.leolimaferreira.adocao_pets_api.model.dto.ErroRespostaDTO;
import io.github.leolimaferreira.adocao_pets_api.model.dto.mapper.AdotanteMapper;
import io.github.leolimaferreira.adocao_pets_api.service.AdotanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adotantes")
@Tag(name = "Adotantes")
public class AdotanteController {

    private final AdotanteService adotanteService;
    private final AdotanteMapper mapper;

    @Operation(summary = "Salvar", description = "Cadastrar novo adotante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "202", description = "Erro de validação."),
            @ApiResponse(responseCode = "409", description = "Adotante já cadastrado.")
    })
    @PostMapping
    public void salvar(@RequestBody @Valid CadastroAdotanteDTO dto) {
        Adotante pet = mapper.toEntity(dto);
        adotanteService.salvar(pet);
    }

    @Operation(summary = "Obter Detalhes", description = "Retorna os dados do adotante pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Adotante encontrado."),
            @ApiResponse(responseCode = "404", description = "Adotante não encontrado.")
    })
    @GetMapping("{id}")
    public ResponseEntity<CadastroAdotanteDTO> obterDetalhes(@PathVariable("id") String id){
        Optional<Adotante> adotanteOptional = adotanteService.obterPorId(UUID.fromString(id));

        return adotanteService
                .obterPorId(UUID.fromString(id))
                .map(adotante -> {
                    CadastroAdotanteDTO dto = mapper.toDTO(adotante);
                    return ResponseEntity.ok(dto);
                }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar", description = "Deleta um adotante existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Adotante não encontrado."),
            @ApiResponse(responseCode = "400", description = "Adotante possui pets cadastrados.")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {
        Optional<Adotante> adotanteOptional = adotanteService.obterPorId(UUID.fromString(id));

        if (adotanteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        adotanteService.deletar(adotanteOptional.get());

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar", description = "Atualiza um adotante existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Adotante não encontrado."),
            @ApiResponse(responseCode = "409", description = "Adotante já cadastrado.")
    })
    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") String id,
                                            @RequestBody @Valid CadastroAdotanteDTO dto) {
        Optional<Adotante> adotanteOptional = adotanteService.obterPorId(UUID.fromString(id));

        if(adotanteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Adotante não cadastrado!", List.of()));
        }

        var adotante = adotanteOptional.get();
        adotante.setNome(dto.nome());
        adotante.setEmail(dto.email());
        adotante.setEndereco(dto.endereco());
        adotante.setTelefone(dto.telefone());
        adotante.setDataNascimento(dto.dataNascimento());

        adotanteService.atualizar(adotante);

        return ResponseEntity.noContent().build();
    }
}
