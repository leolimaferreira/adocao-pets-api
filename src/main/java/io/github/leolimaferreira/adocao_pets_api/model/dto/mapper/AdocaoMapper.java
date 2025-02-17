package io.github.leolimaferreira.adocao_pets_api.model.dto.mapper;

import io.github.leolimaferreira.adocao_pets_api.model.Adocao;
import io.github.leolimaferreira.adocao_pets_api.model.dto.CadastroAdocaoDTO;
import io.github.leolimaferreira.adocao_pets_api.repository.AdotanteRepository;
import io.github.leolimaferreira.adocao_pets_api.repository.PetRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AdocaoMapper {

    @Autowired
    PetRepository petRepository;

    @Autowired
    AdotanteRepository adotanteRepository;

    @Mapping(target = "pet", expression = "java(petRepository.findById(dto.idPet()).orElse(null))")
    @Mapping(target = "adotante", expression = "java(adotanteRepository.findById(dto.idAdotante()).orElse(null))")
    public abstract Adocao toEntity(CadastroAdocaoDTO dto);

    @Mapping(target = "idPet", source = "pet.id")
    @Mapping(target = "idAdotante", source = "adotante.id")
    public abstract CadastroAdocaoDTO toDTO(Adocao adocao);
}
