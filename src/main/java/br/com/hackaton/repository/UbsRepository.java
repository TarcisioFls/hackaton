package br.com.hackaton.repository;

import br.com.hackaton.entity.Ubs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbsRepository extends JpaRepository<Ubs, Long> {

    @Query("SELECT u FROM Ubs u JOIN u.endereco e WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(e.latitude)) * cos(radians(e.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(e.latitude)))) < :distance")
    List<Ubs> buscarUbsPorDistanciaMaxima(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("distance") double distance);

}
