package cl.camisetasguayo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"cl.camisetasguayo"})
@EntityScan(basePackages = {"cl.camisetasguayo"})
@EnableJpaRepositories(basePackages = {"cl.camisetasguayo"})

public class CamisetasGuayoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamisetasGuayoApplication.class, args);
	}

}
