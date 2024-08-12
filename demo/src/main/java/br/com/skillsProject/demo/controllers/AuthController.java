package br.com.skillsProject.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.skillsProject.demo.dtos.LoginRequestDto;
import br.com.skillsProject.demo.dtos.RegisterRequestDto;
import br.com.skillsProject.demo.dtos.ResponseDto;
import br.com.skillsProject.demo.models.Usuario;
import br.com.skillsProject.demo.repositories.UsuarioRepository;
import br.com.skillsProject.demo.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	 UsuarioRepository usuarioRepository;
	@Autowired
	 PasswordEncoder passworEncoder;
	@Autowired
	 TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDto body) {
		Usuario usuario = this.usuarioRepository.findByEmail(body.email())
				.orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
		if (passworEncoder.matches(body.senha(), usuario.getSenha())) {
			String token = this.tokenService.generateToken(usuario);
			return ResponseEntity.ok(new ResponseDto(usuario.getUsername(), token));
		}
		return ResponseEntity.badRequest().build();
	}
	@PostMapping("/cadastrar")
	public ResponseEntity cadastrar(@RequestBody RegisterRequestDto body) {
		Optional<Usuario> usuario = this.usuarioRepository.findByEmail(body.email());
		
		if (usuario.isEmpty()) {			
			Usuario novoUsuario = new Usuario();
			novoUsuario.setSenha(passworEncoder.encode(body.senha()));
			novoUsuario.setEmail(body.email());
			novoUsuario.setUsername(body.username());
			this.usuarioRepository.save(novoUsuario);
			
			String token = this.tokenService.generateToken(novoUsuario);
			return ResponseEntity.ok(new ResponseDto(novoUsuario.getUsername(), token));
		}
		return ResponseEntity.badRequest().build();
	}

}
