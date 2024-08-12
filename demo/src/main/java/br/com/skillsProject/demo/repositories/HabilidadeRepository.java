package br.com.skillsProject.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.skillsProject.demo.models.Habilidade;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long>{
    Optional<Habilidade> findByNomeHab(String nomeHab);
}
