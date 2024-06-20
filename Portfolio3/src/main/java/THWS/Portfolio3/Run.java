package THWS.Portfolio3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import THWS.Portfolio3.server.states.dispatcher.GetDispatcher;

import java.util.Map;

@SpringBootApplication
@RestController
public class Run {

	private final GetDispatcher getDispatcher;

	public Run(GetDispatcher getDispatcher) {
		this.getDispatcher = getDispatcher;
	}

	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
	}

	@GetMapping("/")
	public ResponseEntity<Map<String, String>> getInitialLinks() {
		return ResponseEntity.ok(getDispatcher.getInitialLinks());
	}
}
