package janghowon.terminal;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaAuditing	// JPA Auditing 활성화 -> JPA에서 시간에 대한 값을 자동으로 넣어준다.
@SpringBootApplication
@EnableSwagger2
public class TerminalApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(TerminalApplication.class, args);
	}
}
