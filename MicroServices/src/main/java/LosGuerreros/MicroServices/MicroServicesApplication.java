package LosGuerreros.MicroServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableConfigServer
public class MicroServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroServicesApplication.class, args);
	}

}
