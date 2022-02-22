package no.ntnu;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Here we specify that all the tests (within this class) must be executed with the `rest` profile.
 * I.e., values from application-test.properties will be used
 */
@SpringBootTest
@ActiveProfiles("test")
class SpringProfileApplicationTests {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    private final Logger logger = LoggerFactory.getLogger("Tests");

    @Test
    void contextLoads() {
        logger.info("DB url = " + dbUrl);
    }

}
