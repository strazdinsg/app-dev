package no.ntnu.crudrest;

import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * REST API controller for author collection
 */
@RestController
public class AuthorController {
    private List<Author> authors;

    public AuthorController() {
        initializeData();
    }

    /**
     * Initialize dummy author data for the collection
     */
    private void initializeData() {
        authors = new LinkedList<>();
        authors.add(new Author(1, "James", "Kurose", 1960));
        authors.add(new Author(2, "Keith", "Ross", 1965));
        authors.add(new Author(3, "Jordan", "Peterson", 1960));
    }

}
