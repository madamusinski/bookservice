package pl.madamusinski.book.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.madamusinski.book.exception.ErrorType;
import pl.madamusinski.book.exception.ReadableException;
import pl.madamusinski.book.mapper.BookMapper;
import pl.madamusinski.book.model.Book;
import pl.madamusinski.book.model.BookDocument;

import java.util.List;

@Repository
@AllArgsConstructor
public class MongoBookProvider implements BookDataProvider {

    private final MongoDbBookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream().map(BookMapper::toDto).toList();
    }

    @Override
    public Book saveBook(Book book) {
        return BookMapper.toDto(bookRepository.save(BookMapper.toDocument(book)));
    }

    @Override
    public Book rentBook(String isbn, String borrowerName) {
        var book = bookRepository.findByIsbnAndPerson(isbn);
        return BookMapper.toDto(book.map(b -> setBorrowerForBook(b, borrowerName)).map(bookRepository::save).orElseThrow(() -> new ReadableException(ErrorType.BOOK_NOT_FOUND_BY_ISBN)));
    }

    private BookDocument setBorrowerForBook(BookDocument bookDocument, String borrowerName) {
       bookDocument.setBorrower(borrowerName);
       return bookDocument;
    }
}
