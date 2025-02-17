package io.github.leolimaferreira.adocao_pets_api.model.dto.mapper;

import io.github.leolimaferreira.adocao_pets_api.model.Adotante;
import io.github.leolimaferreira.adocao_pets_api.model.dto.CadastroAdotanteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdotanteMapper {

    Adotante toEntity(CadastroAdotanteDTO dto);

    CadastroAdotanteDTO toDTO(Adotante adotante);

}
