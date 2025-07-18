package br.com.quintinno.credentiumapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.quintinno.credentiumapi.filter.SegurancaFilter;

@Configuration
@EnableWebSecurity
public class SegurancaConfiguration {

	private final SegurancaFilter segurancaFilter;

	public SegurancaConfiguration(SegurancaFilter segurancaFilter) {
		this.segurancaFilter = segurancaFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// .httpBasic(Customizer.withDefaults())
				.addFilterBefore(segurancaFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests(authorize -> authorize
						// .requestMatchers(HttpMethod.GET, "/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/credentium/criptografia/encoder/aes").permitAll()
						.requestMatchers(HttpMethod.POST, "/credentium/criptografia/decoder/aes").permitAll()
						.requestMatchers(HttpMethod.POST, "/credentium/api/usuario/v1").permitAll()
						.requestMatchers(HttpMethod.POST, "/credentium/api/login/v1/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/credentium").permitAll()
						.requestMatchers(HttpMethod.GET, "/actuator/info").permitAll()
						.anyRequest().authenticated())
				.cors(Customizer.withDefaults())
				.csrf(csrf -> csrf.disable());
		return httpSecurity.build();
	}

}
