package eclub.com.conticonnec;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class ContiConnecServiceApplication {

	@Bean
	public ModelMapper modelMapper(){ return new ModelMapper(); }

	@Bean
	public RestTemplate restTemplate(){ return new RestTemplate(); }

	/* El siguiente código integra su metodo main() con Spring Boot.*/
	public static void main(String[] args) {

		/* Application.run(): Este método inicia todo Spring Framework. */
		SpringApplication.run(ContiConnecServiceApplication.class, args);
	}

}
