package br.com.skillsProject.demo.security;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.skillsProject.demo.models.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			String token = JWT.create()
					.withIssuer("SkillsProject")
					.withSubject(usuario.getEmail())
					.withExpiresAt(this.generateExpirationDate())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao autenticar!");
		}
	}
	
	public String ValidateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("SkillsProject")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException e) {
			return null;
		}
	}
	
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
