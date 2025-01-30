package br.com.hackaton.repository;

import br.com.hackaton.entity.Medicamento;
import br.com.hackaton.entity.Tarja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    Optional<Medicamento> findByNomeAndTarja(String nome, Tarja tarja);
}
