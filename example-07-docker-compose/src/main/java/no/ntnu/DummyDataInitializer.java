package no.ntnu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * A class which inserts some dummy data into the database, when Spring Boot app has started
 */
@Component
public class DummyDataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;

    private final Logger logger = LoggerFactory.getLogger("DummyInit");

    /**
     * This method is called when the application is ready (loaded)
     * @param event Event which we don't use :)
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (isDataImported()) {
            logger.info("Data already exists! Not importing again...");
            return;
        }

        logger.info("Importing test data...");
        Author mObama = new Author("Michele", "Obama", 1964);
        Author jPeterson = new Author("Jordan", "Peterson", 1962);
        Author jKurose = new Author("Jim", "Kurose", 1956);
        Author iSpector = new Author("Ian", "Spector", 1988);
        Author kRoss = new Author("Keith", "Ross", 1957);

        Genre gParody = new Genre("Parody");
        Genre gNetworks = new Genre("Computer Networks");
        Genre gPsychology = new Genre("Medical Applied Psychology");
        Genre gBio = new Genre("Autobiography");

        genreRepository.save(gParody);
        genreRepository.save(gNetworks);
        genreRepository.save(gPsychology);
        genreRepository.save(gBio);

        Book bCompNet = new Book("Computer Networking", 2021, 800, gNetworks);
        Book b12Rules = new Book("12 Rules for Life", 2018, 409, gPsychology);
        Book bChuck = new Book("The Truth About Chuck Norris: 400 Facts About the World's Greatest Human",
                2007, 409, gParody);
        Book bBecoming = new Book("Becoming", 2018, 448, gBio);

        bookRepository.save(bCompNet);
        bookRepository.save(b12Rules);
        bookRepository.save(bChuck);
        bookRepository.save(bBecoming);

        jKurose.addBook(bCompNet);
        kRoss.addBook(bCompNet);
        jPeterson.addBook(b12Rules);
        iSpector.addBook(bChuck);
        mObama.addBook(bBecoming);

        authorRepository.save(mObama);
        authorRepository.save(kRoss);
        authorRepository.save(jPeterson);
        authorRepository.save(jKurose);
        authorRepository.save(iSpector);

        logger.info("DONE importing test data");
    }

    /**
     * Check if data is already imported (in the previous run)
     * @return True when database already contains data, false when it does not
     */
    private boolean isDataImported() {
        // Just check if there are any genres already stored
        return genreRepository.count() > 0;
    }
}
