package io.github.leolimaferreira.adocao_pets_api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "adocao")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id_pet", nullable = false)
    private Pet pet;

    @OneToOne
    @JoinColumn(name = "id_adotante", nullable = false)
    private Adotante adotante;

    @CreatedDate
    @Column(name = "data_adocao")
    private LocalDateTime dataAdocao;

}
