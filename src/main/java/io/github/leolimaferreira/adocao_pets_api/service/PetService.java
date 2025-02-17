package io.github.leolimaferreira.adocao_pets_api.service;

import io.github.leolimaferreira.adocao_pets_api.common.exception.EntidadeNotFoundException;
import io.github.leolimaferreira.adocao_pets_api.model.Pet;
import io.github.leolimaferreira.adocao_pets_api.model.dto.mapper.PetMapper;
import io.github.leolimaferreira.adocao_pets_api.repository.AdotanteRepository;
import io.github.leolimaferreira.adocao_pets_api.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void deletar(Pet pet) {petRepository.delete(pet);}

    public void atualizar(Pet pet) {
        petRepository.save(pet);
    }

}
