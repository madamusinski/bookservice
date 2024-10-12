package pl.madamusinski.book.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.madamusinski.book.mapper.BookMapper;
import pl.madamusinski.book.model.Book;

import java.util.List;

import static pl.madamusinski.book.mapper.BookMapper.*;

@Repository
@RequiredArgsConstructor
public class SpringDataBookRepository implements BookRentalInformationRepository {

    private final SpringDataBasedBookRepository springDataBasedBookRepository;

    @Override
    public List<Book> getAllRentedBooks() {
        return springDataBasedBookRepository.findAllRented().stream().map(BookMapper::toDto).toList();
    }

    public Book getBookByIsbn(String isbn) {
        return springDataBasedBookRepository.findBookByIsbn(isbn).map(BookMapper::toDto).orElse(null);
    }

    @Override
    public void saveBook(Book book) {
        toDto(springDataBasedBookRepository.save(toEntity(book)));
    }
}
