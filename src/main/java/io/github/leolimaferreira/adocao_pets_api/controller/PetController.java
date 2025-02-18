package io.github.leolimaferreira.adocao_pets_api.controller;

import io.github.leolimaferreira.adocao_pets_api.model.Pet;
import io.github.leolimaferreira.adocao_pets_api.model.dto.CadastroPetDTO;
import io.github.leolimaferreira.adocao_pets_api.model.dto.ErroRespostaDTO;
import io.github.leolimaferreira.adocao_pets_api.model.dto.ResultadoPesquisaPetDTO;
import io.github.leolimaferreira.adocao_pets_api.model.dto.mapper.PetMapper;
import io.github.leolimaferreira.adocao_pets_api.service.PetService;
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
@RequestMapping("/pets")
@RequiredArgsConstructor
@Tag(name = "Pets")
public class PetController {

    private final PetService petService;
    private final PetMapper mapper;

    @Operation(summary = "Salvar", description = "Cadastrar novo pet")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "202", description = "Erro de validação."),
            @ApiResponse(responseCode = "409", description = "Pet já cadastrado.")
    })
    @PostMapping
    public void salvar(@RequestBody @Valid CadastroPetDTO dto) {
        Pet pet = mapper.toEntity(dto);
        //pet.setDataCadastro(LocalDateTime.now());
        petService.salvar(pet);
    }

    @Operation(summary = "Obter Detalhes", description = "Retorna os dados do pet pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet encontrado."),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado.")
    })
    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaPetDTO> obterDetalhesPorId(@PathVariable("id") String id) {

        return petService.obterPorId(UUID.fromString(id))
                .map(pet -> {
                    var dto = mapper.toDTO(pet);
                    return ResponseEntity.ok(dto);
                }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar", description = "Deleta um pet existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado."),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") String id) {
        Optional<Pet> petOptional = petService.obterPorId(UUID.fromString(id));

        if(petOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        petService.deletar(petOptional.get());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar", description = "Atualiza um pet existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado."),
            @ApiResponse(responseCode = "409", description = "Pet já cadastrado.")
    })
    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") String id, @RequestBody  @Valid CadastroPetDTO dto) {

        return petService.obterPorId(UUID.fromString(id))
                .map(pet -> {
                    Pet entidadeAux = mapper.toEntity(dto);
                    pet.setNome(entidadeAux.getNome());
                    pet.setEspecie(entidadeAux.getEspecie());
                    pet.setIdade(entidadeAux.getIdade());
                    pet.setStatus(entidadeAux.getStatus());
                    pet.setDescricao(entidadeAux.getDescricao());
                    pet.setAdotante(entidadeAux.getAdotante());

                    petService.atualizar(pet);

                    return ResponseEntity.noContent().build();
                }).orElseGet( () -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Pet não cadastrado!", List.of())));

    }


}
