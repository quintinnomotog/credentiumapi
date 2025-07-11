package br.com.quintinno.credentiumapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
	
	private static final String[] METHOD_LIST = { "GET", "POST", "DELETE", "PUT" };
	
	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods(METHOD_LIST);
	}

}
