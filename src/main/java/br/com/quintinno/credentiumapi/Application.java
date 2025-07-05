package br.com.quintinno.credentiumapi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Value("${spring.application.name}")
	private String nomeAPI;
	
	@Value("${server.port}")
	private String portaAPI;
	
	private static final String BUILD_DATE = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(getInfoAPI());
	}
	
	@GetMapping({"", "/"})
	public String getInfoAPI() {
		return String.format("[ Application: %s | Version: %s | Port: %s | Build: %s ]", 
				nomeAPI, getClass().getPackage().getImplementationVersion(), portaAPI, BUILD_DATE);
	}

}
