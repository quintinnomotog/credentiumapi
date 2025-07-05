package br.com.quintinno.credentiumapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(getInfoAPI());
	}
	
	@GetMapping({"", "/"})
	public String getInfoAPI() {
		return "[ Application: CredentiumAPI | "
				.concat("Version: v1.0.0.0 | ")
				.concat("Port: 8081 | ")
				.concat("Build: 05/07/2025 13:35:54 ]");
	}

}
