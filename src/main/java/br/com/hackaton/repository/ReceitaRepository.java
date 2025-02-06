package br.com.hackaton.repository;

import br.com.hackaton.entity.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Page<Receita> findByMedicoId(Long id, PageRequest pageRequest);
}
