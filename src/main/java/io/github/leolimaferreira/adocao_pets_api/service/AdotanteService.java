package io.github.leolimaferreira.adocao_pets_api.service;

import io.github.leolimaferreira.adocao_pets_api.common.exception.EntidadeNotFoundException;
import io.github.leolimaferreira.adocao_pets_api.common.exception.OperacaoNaoPermitidaException;
import io.github.leolimaferreira.adocao_pets_api.model.Adotante;
import io.github.leolimaferreira.adocao_pets_api.repository.AdotanteRepository;
import io.github.leolimaferreira.adocao_pets_api.repository.PetRepository;
import io.github.leolimaferreira.adocao_pets_api.validator.AdotanteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdotanteService {

    private final AdotanteRepository adotanteRepository;
    private final PetRepository petRepository;
    private final AdotanteValidator adotanteValidator;

    public Adotante salvar(Adotante adotante) {
        adotanteValidator.validar(adotante);
        return adotanteRepository.save(adotante);
    }

    public Optional<Adotante> obterPorId(UUID id) {return adotanteRepository.findById(id);}

    public void deletar(Adotante adotante) {
        if (possuiPet(adotante)) {
            throw new OperacaoNaoPermitidaException(
                    "Não é permitido excluir um adotante que tenha pets!"
            );
        }
        adotanteRepository.delete(adotante);
    }

    public void atualizar(Adotante adotante) {
        adotanteValidator.validar(adotante);
        adotanteRepository.save(adotante);
    }

    public boolean possuiPet(Adotante adotante) {return petRepository.existsByAdotante(adotante);}

}
