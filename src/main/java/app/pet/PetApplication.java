package app.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PetApplication {
	@GetMapping("/")
	public String home() {
		return "Hello Docker World from Spring Boot!";
	}
	public static void main(String[] args) {
		SpringApplication.run(PetApplication.class, args);
	}

}
