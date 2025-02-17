package io.github.leolimaferreira.adocao_pets_api.controller;

import io.github.leolimaferreira.adocao_pets_api.model.Adocao;
import io.github.leolimaferreira.adocao_pets_api.model.Adotante;
import io.github.leolimaferreira.adocao_pets_api.model.Pet;
import io.github.leolimaferreira.adocao_pets_api.model.StatusPet;
import io.github.leolimaferreira.adocao_pets_api.model.dto.CadastroAdocaoDTO;
import io.github.leolimaferreira.adocao_pets_api.model.dto.ErroRespostaDTO;
import io.github.leolimaferreira.adocao_pets_api.model.dto.mapper.AdocaoMapper;
import io.github.leolimaferreira.adocao_pets_api.service.AdocaoService;
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
public class AdocaoController {

    private final AdocaoService adocaoService;
    private final AdocaoMapper mapper;

    @PostMapping
    public void salvar(@RequestBody @Valid CadastroAdocaoDTO dto) {
        Optional<Adotante> adotanteOptional = adocaoService.obterAdotantePorId(dto.idAdotante());
        Optional<Pet> petOptional = adocaoService.obterPetPorId(dto.idPet());
        Adocao adocao = mapper.toEntity(dto);
        adocao.setPet(petOptional.get());
        adocao.setAdotante(adotanteOptional.get());
        petOptional.get().setStatus(StatusPet.ADOTADO);
        adocaoService.salvar(adocao);

    }

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

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {
        Optional<Adocao> adocaoOptional = adocaoService.obterAdocaoPorId(UUID.fromString(id));

        if (adocaoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Pet> petOptional = adocaoService.obterPetPorId(adocaoOptional.get().getPet().getId());
        petOptional.get().setStatus(StatusPet.DISPONIVEL);

        adocaoService.deletar(adocaoOptional.get());

        return ResponseEntity.noContent().build();
    }
}
