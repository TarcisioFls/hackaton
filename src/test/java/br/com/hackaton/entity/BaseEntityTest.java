package br.com.hackaton.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseEntityTest {

    private BaseEntity baseEntity;

    @BeforeEach
    void setUp() {
        baseEntity = new BaseEntity();
    }

    @Test
    void testPrePersist() {
        baseEntity.prePersist();
        assertNotNull(baseEntity.getDataHoraCriacao());
    }

    @Test
    void testPreUpdate() {
        baseEntity.preUpdate();
        assertNotNull(baseEntity.getDataHoraAtualizacao());
    }

    @Test
    void testGetId() {
        var id = 1L;
        baseEntity = new BaseEntity(id);
        assertEquals(id, baseEntity.getId());
    }

    @Test
    void testGetDataHoraCriacao() {
        baseEntity.prePersist();
        assertEquals(LocalDateTime.now().getDayOfMonth(), baseEntity.getDataHoraCriacao().getDayOfMonth());
    }

    @Test
    void testGetDataHoraAtualizacao() {
        baseEntity.preUpdate();
        assertEquals(LocalDateTime.now().getDayOfMonth(), baseEntity.getDataHoraAtualizacao().getDayOfMonth());
    }
}