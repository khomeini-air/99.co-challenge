package co99.khomeini.challenge.application.listing.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Configuration
@ComponentScan
@EntityScan("co99.khomeini.challenge.application.listing.dao.entity")
@EnableJpaRepositories("co99.khomeini.challenge.application.listing.dao.repository")
@PropertySource("classpath:db-config.properties")
public class ListingConfiguration {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public DataSource dataSource() {
        logger.info("dataSource() invoked");

        // Create an in-memory H2 relational database
        DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:db-schema/listing-schema.sql")
                .addScript("classpath:db-schema/listing-data.sql").build();

        logger.info("dataSource = " + dataSource);

        // Sanity check
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listing_counts = jdbcTemplate.queryForList("SELECT count(id) FROM listings");
        logger.info("***********listing_counts: " + listing_counts);

        return dataSource;
    }

}
