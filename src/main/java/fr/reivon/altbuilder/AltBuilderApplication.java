package fr.reivon.altbuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class AltBuilderApplication {

	/**
	 * Le code legacy : spring boot + mongo + kafka + redis
	 * Le nouveau code : Quarkus + postgres + kafka
	 * Et on code selon les principes d'une archi hexagonale
	 * Un peu d'angular sur un outil de supervision
	 */

	private static final Logger log = LoggerFactory.getLogger(AltBuilderApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(AltBuilderApplication.class, args);
	}

	/**
	 * https://docs.spring.io/spring-boot/how-to/properties-and-configuration.html
	 * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
	 */
//	@PostConstruct
//	public void initApplication() {
//		if (log.isInfoEnabled()) {
//			log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
//		}
//		Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
//		if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
//			log.error("You have misconfigured your application! It should not run " +
//					"with both the 'dev' and 'prod' profiles at the same time.");
//		}
//	}

}
