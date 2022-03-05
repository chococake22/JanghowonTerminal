package janghowon.terminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TerminalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerminalApplication.class, args);
	}

}
