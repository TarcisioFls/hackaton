package br.com.hackaton.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora_criacao")
    private LocalDateTime dataHoraCriacao;

    @Column(name = "data_hora_atualizacao")
    private LocalDateTime dataHoraAtualizacao;

    public BaseEntity() {}

    public BaseEntity(Long id) {

        this.id = id;
    }

    @PrePersist
    public void prePersist() {
        this.dataHoraCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

}
