package br.com.skillsProject.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.skillsProject.demo.models.HabilidadeDoUsuario;
import br.com.skillsProject.demo.models.Usuario;

public interface HabilidadeDoUsuarioRepository extends JpaRepository<HabilidadeDoUsuario, Long> {
    List<HabilidadeDoUsuario> findByUsuario(Usuario usuario);
}
