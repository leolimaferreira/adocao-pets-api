package io.github.leolimaferreira.adocao_pets_api.validator;

import io.github.leolimaferreira.adocao_pets_api.common.exception.RegistroDuplicadoException;
import io.github.leolimaferreira.adocao_pets_api.model.Adotante;
import io.github.leolimaferreira.adocao_pets_api.repository.AdotanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdotanteValidator {

    private final AdotanteRepository adotanteRepository;

    public void validar(Adotante adotante) {
        if(existeAdotanteCadastrado(adotante)) {
            throw new RegistroDuplicadoException("Adotante já cadastrado com esse email!");
        }
    }

    private boolean existeAdotanteCadastrado(Adotante adotante) {
        Optional<Adotante> adotanteOptional = adotanteRepository.findByEmail(adotante.getEmail());
        if(adotante.getId() == null) {
            return adotanteOptional.isPresent();
        }

        return !adotante.getId().equals(adotanteOptional.get().getId()) && adotanteOptional.isPresent();
    }

}
