package io.github.leolimaferreira.adocao_pets_api.service;

import io.github.leolimaferreira.adocao_pets_api.common.exception.OperacaoNaoPermitidaException;
import io.github.leolimaferreira.adocao_pets_api.model.Pet;
import io.github.leolimaferreira.adocao_pets_api.repository.AdotanteRepository;
import io.github.leolimaferreira.adocao_pets_api.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PetService {

    private final AdotanteRepository adotanteRepository;
    private final PetRepository petRepository;

    public Pet salvar(Pet pet) {
        return petRepository.save(pet);
    }

    public Optional<Pet> obterPorId(UUID id) {return petRepository.findById(id);}

    public void deletar(Pet pet) {
        if (possuiAdotante(pet)){
            throw new OperacaoNaoPermitidaException(
                    "Não é permitido excluir um pet que tenha um adotante!"
            );
        }
        petRepository.delete(pet);
    }

    public void atualizar(Pet pet) {
        petRepository.save(pet);
    }

    public boolean possuiAdotante(Pet pet) {return adotanteRepository.existsByPetsContains(pet);}

}
