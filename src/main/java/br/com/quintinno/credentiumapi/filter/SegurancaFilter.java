package br.com.quintinno.credentiumapi.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.quintinno.credentiumapi.entity.UsuarioEntity;
import br.com.quintinno.credentiumapi.repository.UsuarioRepository;
import br.com.quintinno.credentiumapi.service.TokenService;
import br.com.quintinno.credentiumapi.service.UsuarioService;
import br.com.quintinno.credentiumapi.utility.SegurancaUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SegurancaFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(SegurancaFilter.class);

	private final TokenService tokenService;

	private final UsuarioRepository usuarioRepository;

	private final UsuarioService usuarioService;

	private static final String AUTHORIZATION = "Authorization";

	private static final String BEARER = "Bearer ";

	private static final String ROLE_USER = "ROLE_USER";

	public SegurancaFilter(TokenService tokenService, UsuarioRepository usuarioRepository,
			UsuarioService usuarioService) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
		this.usuarioService = usuarioService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// Recebe: /credenctium/**
		String urlRequisicao = request.getRequestURI();

		if (SegurancaUtility.isPublica(urlRequisicao)) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = this.recuperarTokenRequisicao(request);
		String identificador = this.tokenService.extrairIdentificadorToken(token);
		if (identificador != null) {
			Optional<UsuarioEntity> usuarioEntityOptional = this.usuarioRepository.findByIdentificador(identificador)
					.stream().findFirst();
			if (usuarioEntityOptional.isEmpty()) {
				// FIXME: Criar Filter de TraceID para mapear cada requisicao de ponta a ponta
				log.warn("CredentiumAPI [SegurancaFilter]: Usuário com identificador {} não encontrado!",
						identificador);
				filterChain.doFilter(request, response);
				return;
			}
			UsuarioEntity usuarioEntity = this.usuarioService.recuperarUsuarioEntity(identificador);
			if (usuarioEntity == null) {
				log.warn("CredentiumAPI [SegurancaFilter]: Usuário com identificador {} não encontrado!",
						identificador);
				new RuntimeException("Usuário não Encontrado na Base de Dados!");
			}
			List<GrantedAuthority> roleList = Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER));
			UsernamePasswordAuthenticationToken authententication = new UsernamePasswordAuthenticationToken(
					usuarioEntity, null, roleList);
			SecurityContextHolder.getContext().setAuthentication(authententication);
		}
		filterChain.doFilter(request, response);
	}

	/**
	 * Extrai token enviado no cabecalho da requisicao
	 * 
	 * @param httpServletRequest
	 * @return Retorna o Token sem prefixo "Bearer"
	 */
	private String recuperarTokenRequisicao(HttpServletRequest httpServletRequest) {
		String header = httpServletRequest.getHeader(AUTHORIZATION);
		if (header == null || !header.startsWith(BEARER)) {
			log.warn("CredentiumAPI [SegurancaFilter]: Token inválido!");
			return null;
		}
		return header.replace(BEARER, "");
	}

}
