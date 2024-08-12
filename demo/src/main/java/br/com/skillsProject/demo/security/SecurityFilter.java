package br.com.skillsProject.demo.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.skillsProject.demo.models.Usuario;
import br.com.skillsProject.demo.repositories.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	@Autowired
	TokenService tokenService;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		var token = this.recoverToken(request);
//		var login = this.tokenService.ValidateToken(token);
//		
//		if(login != null) {
//			Usuario usuario = usuarioRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
//			var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//		}
		filterChain.doFilter(request, response);
	}
		
	private String recoverToken(HttpServletRequest request) {
//	    var authHeader = request.getHeader("Authorization");
//	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//	        return null;
//	    }
//	    return authHeader.substring(7).trim();
		return null; //se descomentar a parte de cima, apague esse return
	}



}
