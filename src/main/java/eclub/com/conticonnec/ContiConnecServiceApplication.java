package eclub.com.conticonnec;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "eCLUB Conti API", version = "1.0",
		description = "Envio de"))
public class ContiConnecServiceApplication {

	@Bean
	public ModelMapper modelMapper(){ return new ModelMapper(); }

	@Bean
	public RestTemplate restTemplate(){ return new RestTemplate(); }

	//El siguiente código integra el metodo "main()" con Spring Boot.
	public static void main(String[] args) {
		//Application.run(): Este método inicia todo el Framework Spring.
		SpringApplication.run(ContiConnecServiceApplication.class, args);
	}

}
