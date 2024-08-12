package br.com.skillsProject.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.skillsProject.demo.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByUsername(String username);
	Optional<Usuario> findById(Long idUsuario);
	Optional <Usuario> findByEmail(String email);
}
