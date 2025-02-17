package io.github.leolimaferreira.adocao_pets_api.repository;

import io.github.leolimaferreira.adocao_pets_api.model.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdocaoRepository extends JpaRepository<Adocao, UUID> {
}
