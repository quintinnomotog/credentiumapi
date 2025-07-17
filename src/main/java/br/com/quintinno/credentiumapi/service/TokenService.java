package br.com.quintinno.credentiumapi.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.quintinno.credentiumapi.entity.UsuarioEntity;

@Service
public class TokenService {
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(TokenService.class);
	
	@Value("${application.token.secret}")
	private String SECRET_ALGORITHM;
	
	@Value("${spring.application.name}")
	private String ORIGIN;
	
	@Value("${application.token.expiration}")
	private long EXPIRATION;
	
	public String generateToken(UsuarioEntity usuarioEntity) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET_ALGORITHM);
			return JWT.create()
					.withIssuer(ORIGIN)
					.withSubject(usuarioEntity.getIdentificador())
					.withClaim("code", usuarioEntity.getCodePublic())
					.withExpiresAt(getTempoExpiracao())
					.sign(algorithm);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar Token do Usuário!");
		}
	}
	
	private Date getTempoExpiracao() {
		return Date.from(Instant.now().plus(Duration.ofHours(EXPIRATION)));
	}
	
	public String extrairIdentificadorToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET_ALGORITHM);
			return JWT.require(algorithm)
					.withIssuer(ORIGIN)
					.build()
					.verify(token)
					.getSubject();
		} catch (Exception e) {
			logger.warn("CredentiumAPI [TokenService.class]: Falha ao validar token do usuário: {}", e.getMessage(), e);
			return null;
		}
	}
	
}
