package io.github.leolimaferreira.adocao_pets_api.model.dto.mapper;

import io.github.leolimaferreira.adocao_pets_api.model.Pet;
import io.github.leolimaferreira.adocao_pets_api.model.dto.CadastroPetDTO;
import io.github.leolimaferreira.adocao_pets_api.model.dto.ResultadoPesquisaPetDTO;
import io.github.leolimaferreira.adocao_pets_api.repository.AdotanteRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AdotanteMapper.class)
public abstract class PetMapper {

    @Autowired
    AdotanteRepository adotanteRepository;

    //@Mapping(target = "adotante", expression = "java( adotanteRepository.findById(dto.idAdotante()).orElse(null) )")
    public abstract Pet toEntity(CadastroPetDTO dto);

    public abstract ResultadoPesquisaPetDTO toDTO(Pet pet);

}
