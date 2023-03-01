package no.ntnu.crudrest.repositories;

import no.ntnu.crudrest.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Handle SQL database access, for books.
 */
public interface BookRepository extends CrudRepository<Book, Integer> {
  // Iterable<Book> findAllByTitleContaining(String title);
  /**
   * Find all books, use pagination.
   *
   * @param pageable Pagination configuration - limit and offset.
   *                 Use PageRequest.of(page, limit, sorting).
   * @return All books with given paging/limit
   */
  Page<Book> findAll(Pageable pageable);
}
