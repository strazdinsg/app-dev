package ntnu.ad.library.libraryjpadb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public String createBook(Book book){
        try{
            if(!bookRepository.existsById(book.getId())){
                bookRepository.save(book);
                return "Book inserted!";
            }else{
                return "Book already exists in the DB";
            }
        }catch (Exception e){
            throw e;
        }

    }

    public List<Book> readBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public String updateBook(Book book){
        if(bookRepository.existsById(book.getId())){
            try{
                Book bookToBeUpdate = bookRepository.findById(book.getId()).get();
                bookToBeUpdate.setName(book.getName());
                bookToBeUpdate.setPublisher(book.getPublisher());
                bookRepository.save(bookToBeUpdate);
                return "Book info updated!";
            }catch(Exception e){
                throw e;
            }
        }else{
            return "Book does not exist in DB";
        }
    }

    @Transactional
    public String deleteBook(Book book){
        if(bookRepository.existsById(book.getId())){
            try{
                bookRepository.delete(book);
                return "Book is deleted successfully!";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Book does not exist in DB";
        }
    }
}
