package br.com.hackaton.repository;

import br.com.hackaton.entity.Ubs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbsRepository extends JpaRepository<Ubs, Long> {
}
