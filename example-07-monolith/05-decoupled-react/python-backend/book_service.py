from book_repository import BookRepository


class BookService:
    """ A service for book related operations. """

    @staticmethod
    def get_all_books():
        return BookRepository.get_all_books()

    @staticmethod
    def get_favorites():
        return BookRepository.get_first(2)


# Singleton bookService
bookService = BookService()
