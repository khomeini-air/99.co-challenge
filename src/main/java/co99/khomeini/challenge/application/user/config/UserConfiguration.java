package co99.khomeini.challenge.application.user.config;

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
@EntityScan("co99.khomeini.challenge.application.user.dao.entity")
@EnableJpaRepositories("co99.khomeini.challenge.application.user.dao.repository")
@PropertySource("classpath:db-config.properties")
public class UserConfiguration {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public DataSource dataSource() {
        logger.info("dataSource() invoked");

        // Create an in-memory H2 relational database
        DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:db-schema/user-schema.sql")
                .addScript("classpath:db-schema/user-data.sql").build();

        logger.info("dataSource = " + dataSource);

        // Sanity check
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> user_counts = jdbcTemplate.queryForList("SELECT count(id) FROM users");
        logger.info("***********user_counts: " + user_counts);

        return dataSource;
    }
}
