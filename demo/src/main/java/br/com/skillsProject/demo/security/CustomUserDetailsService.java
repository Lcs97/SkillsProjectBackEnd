package br.com.skillsProject.demo.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.skillsProject.demo.models.Usuario;
import br.com.skillsProject.demo.repositories.UsuarioRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByEmail(username)
			.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
		
		return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
	}
}
