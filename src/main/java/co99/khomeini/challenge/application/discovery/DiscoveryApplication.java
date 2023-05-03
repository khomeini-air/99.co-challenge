package co99.khomeini.challenge.application.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableEurekaServer
public class DiscoveryApplication {
	public static final String DISCOVERY_SERVER_HOSTNAME = "discovery.server.hostname";

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "discovery-application");
		SpringApplication.run(DiscoveryApplication.class, args);
	}
}
