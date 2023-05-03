package co99.khomeini.challenge.application.listing;

import co99.khomeini.challenge.application.discovery.DiscoveryApplication;
import co99.khomeini.challenge.application.listing.config.ListingConfiguration;
import co99.khomeini.challenge.application.listing.dao.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import(ListingConfiguration.class)
public class ListingApplication {
	@Autowired
	private ListingRepository repository;

	public static void main(String[] args) {
		if (System.getProperty(DiscoveryApplication.DISCOVERY_SERVER_HOSTNAME) == null)
			System.setProperty(DiscoveryApplication.DISCOVERY_SERVER_HOSTNAME, "localhost");

		System.setProperty("spring.config.name", "listing-application");

		SpringApplication.run(ListingApplication.class, args);
	}

}
