package io.github.leolimaferreira.adocao_pets_api.controller;

import io.github.leolimaferreira.adocao_pets_api.model.Adocao;
import io.github.leolimaferreira.adocao_pets_api.model.Adotante;
import io.github.leolimaferreira.adocao_pets_api.model.Pet;
import io.github.leolimaferreira.adocao_pets_api.model.StatusPet;
import io.github.leolimaferreira.adocao_pets_api.model.dto.CadastroAdocaoDTO;
import io.github.leolimaferreira.adocao_pets_api.model.dto.ErroRespostaDTO;
import io.github.leolimaferreira.adocao_pets_api.model.dto.mapper.AdocaoMapper;
import io.github.leolimaferreira.adocao_pets_api.service.AdocaoService;
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
@RequestMapping("/adocao")
@RequiredArgsConstructor
@Tag(name = "Adoções")
public class AdocaoController {

    private final AdocaoService adocaoService;
    private final AdocaoMapper mapper;
    private final AdotanteService adotanteService;

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar nova adoção")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "202", description = "Erro de validação."),
            @ApiResponse(responseCode = "409", description = "Adoção já cadastrada.")
    })
    public void salvar(@RequestBody @Valid CadastroAdocaoDTO dto) {
        Optional<Adotante> adotanteOptional = adocaoService.obterAdotantePorId(dto.idAdotante());
        Optional<Pet> petOptional = adocaoService.obterPetPorId(dto.idPet());
        Adocao adocao = mapper.toEntity(dto);

        if(adotanteOptional.isPresent() && petOptional.isPresent()) {
            Adotante adotante = adotanteOptional.get();
            Pet pet = petOptional.get();

            adotante.getPets().add(pet);

            petOptional.get().setStatus(StatusPet.ADOTADO);
            petOptional.get().setAdotante(adotante);

            adocao.setPet(petOptional.get());
            adocao.setAdotante(adotanteOptional.get());
            adocaoService.salvar(adocao);

            adotanteService.atualizar(adotante);
        }
    }

    @Operation(summary = "Obter Detalhes", description = "Retorna os dados da adoção pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Adoção encontrada."),
            @ApiResponse(responseCode = "404", description = "Adoção não encontrada.")
    })
    @GetMapping("{id}")
    public ResponseEntity<CadastroAdocaoDTO> obterDetalhes(@PathVariable("id") String id) {
        Optional<Adocao> adocaoOptional = adocaoService.obterAdocaoPorId(UUID.fromString(id));

        return adocaoService
                .obterAdocaoPorId(UUID.fromString(id))
                .map(adocao -> {
                    CadastroAdocaoDTO dto = mapper.toDTO(adocao);
                    return ResponseEntity.ok(dto);
                }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar", description = "Deleta uma adoção existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Adoção não encontrada."),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {
        Optional<Adocao> adocaoOptional = adocaoService.obterAdocaoPorId(UUID.fromString(id));

        if (adocaoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Pet> petOptional = adocaoService.obterPetPorId(adocaoOptional.get().getPet().getId());
        petOptional.get().setAdotante(null);
        petOptional.get().setStatus(StatusPet.DISPONIVEL);

        Optional<Adotante> adotanteOptional = adocaoService.obterAdotantePorId(adocaoOptional.get().getAdotante().getId());
        adotanteOptional.get().getPets().remove(petOptional.get());

        adocaoService.deletar(adocaoOptional.get());
        adotanteService.atualizar(adotanteOptional.get());

        return ResponseEntity.noContent().build();
    }
}
