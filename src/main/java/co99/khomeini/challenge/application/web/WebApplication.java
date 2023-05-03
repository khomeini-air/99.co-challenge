package co99.khomeini.challenge.application.web;

import co99.khomeini.challenge.application.discovery.DiscoveryApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
public class WebApplication {
	public static final String LISTING_SERVICE_URL = "http://LISTING-SERVICE";

	public static void main(String[] args) {
		// Default to registration server on localhost
		if (System.getProperty(DiscoveryApplication.DISCOVERY_SERVER_HOSTNAME) == null)
			System.setProperty(DiscoveryApplication.DISCOVERY_SERVER_HOSTNAME, "localhost");

		// Tell server to look for accounts-server.properties or
		// accounts-server.yml
		System.setProperty("spring.config.name", "web-application");

		SpringApplication.run(WebApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
