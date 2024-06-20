package fr.reivon.altbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlteredBuilderApplication {

	/**
	 * Le code legacy : spring boot + mongo + kafka + redis
	 * Le nouveau code : Quarkus + postgres + kafka
	 * Et on code selon les principes d'une archi hexagonale
	 * Un peu d'angular sur un outil de supervision
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(AlteredBuilderApplication.class, args);
	}

}
