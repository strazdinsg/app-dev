package no.ntnu;

import no.ntnu.genre.Genre;
import no.ntnu.genre.GenreRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Here we specify that all the tests (within this class) must be executed with the `test` profile.
 * I.e., values from application-test.properties will be used
 */
@SpringBootTest
@ActiveProfiles("test")
class SpringProfileApplicationTests {
  @Value("${spring.datasource.url}")
  private String dbUrl;
  @Autowired
  private GenreRepository genreRepository;

  private final Logger logger = LoggerFactory.getLogger("Tests");

  @Test
  void contextLoads() {
    logger.info("DB url = " + dbUrl);
    assertThat("Correct DB URL", "jdbc:derby:memory:local;create=true".equals(dbUrl));
  }

  /**
   * Tests involving database access and modification. These tests are run with an in-memory
   * database used only for testing. Therefore, no production data (nor development-data) is
   * destroyed by the tests.
   */
  @Test
  void genreDbCrudTest() {
    assertThat("No genres by default", genreRepository.count() == 0);

    // Now insert a genre
    Genre genre = new Genre("Autobiographies");
    genreRepository.save(genre);
    assertThat("ID assigned", genre.getId() > 0);
    assertThat("One genre inserted", genreRepository.count() == 1);

    // Select the same genre
    Optional<Genre> g2 = genreRepository.findById(genre.getId());
    assertThat("Genre selected", g2.isPresent());
    Genre genre2 = g2.get();
    assertThat("Genre is equal to original", genre.equals(genre2));

    // Try to select non-existing genre
    assertThat("Selecting non-existing genre", genreRepository.findById(667).isEmpty());

    // Insert another genre
    Genre genre3 = new Genre("Fiction");
    genreRepository.save(genre3);
    assertThat("ID assigned", genre3.getId() > genre.getId());
    assertThat("Two genres inserted", genreRepository.count() == 2);

    genreRepository.delete(genre);
    assertThat("One genre deleted", genreRepository.count() == 1);

    genreRepository.delete(genre);
    assertThat("Can't delete the same genre twice", genreRepository.count() == 1);

    genreRepository.delete(genre3);
    assertThat("Both genres deleted", genreRepository.count() == 0);
  }

}
