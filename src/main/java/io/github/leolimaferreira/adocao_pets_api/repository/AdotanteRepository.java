package io.github.leolimaferreira.adocao_pets_api.repository;

import io.github.leolimaferreira.adocao_pets_api.model.Adotante;
import io.github.leolimaferreira.adocao_pets_api.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdotanteRepository extends JpaRepository<Adotante, UUID> {
    Optional<Adotante> findById(UUID idAdotante);

    Optional<Adotante> findByEmail(String email);

    boolean existsByPetsContains(Pet pet);
}
