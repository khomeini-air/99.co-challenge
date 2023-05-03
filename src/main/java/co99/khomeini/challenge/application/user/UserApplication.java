package co99.khomeini.challenge.application.user;

import co99.khomeini.challenge.application.discovery.DiscoveryApplication;
import co99.khomeini.challenge.application.user.config.UserConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import(UserConfiguration.class)
public class UserApplication {

	public static void main(String[] args) {
		// Default to registration server on localhost
		if (System.getProperty(DiscoveryApplication.DISCOVERY_SERVER_HOSTNAME) == null)
			System.setProperty(DiscoveryApplication.DISCOVERY_SERVER_HOSTNAME, "localhost");

		// Tell server to look for user-server.properties or
		// user-server.yml
		System.setProperty("spring.config.name", "user-application");

		SpringApplication.run(UserApplication.class, args);
	}

}
