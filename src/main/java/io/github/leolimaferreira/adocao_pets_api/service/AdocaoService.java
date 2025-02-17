package io.github.leolimaferreira.adocao_pets_api.service;

import io.github.leolimaferreira.adocao_pets_api.model.Adocao;
import io.github.leolimaferreira.adocao_pets_api.model.Adotante;
import io.github.leolimaferreira.adocao_pets_api.model.Pet;
import io.github.leolimaferreira.adocao_pets_api.repository.AdocaoRepository;
import io.github.leolimaferreira.adocao_pets_api.repository.AdotanteRepository;
import io.github.leolimaferreira.adocao_pets_api.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final AdotanteRepository adotanteRepository;
    private final PetRepository petRepository;

    public Optional<Adotante> obterAdotantePorId(UUID id) {return adotanteRepository.findById(id);}

    public Optional<Pet> obterPetPorId(UUID id) {return petRepository.findById(id);}

    public void salvar(Adocao adocao) {adocaoRepository.save(adocao);}

    public Optional<Adocao> obterAdocaoPorId(UUID id) {return adocaoRepository.findById(id);}

    public void deletar(Adocao adocao) {adocaoRepository.delete(adocao);}
}
