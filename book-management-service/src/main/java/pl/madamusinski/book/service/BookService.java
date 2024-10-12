package pl.madamusinski.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.madamusinski.book.events.EventPublisher;
import pl.madamusinski.book.mapper.BookMapper;
import pl.madamusinski.book.model.Book;
import pl.madamusinski.book.model.BookResponse;
import pl.madamusinski.book.repository.BookDataProvider;

import java.time.Clock;
import java.util.List;

import static pl.madamusinski.book.events.model.BookUpdatedEvent.*;
import static pl.madamusinski.book.mapper.BookMapper.*;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookDataProvider bookProvider;
    private final EventPublisher eventPublisher;
    private final Clock clock;

    public List<BookResponse> getAllBooks() {
        return bookProvider.getAllBooks().stream().map(BookMapper::toResponse).toList();
    }

    public BookResponse createBook(Book book) {
        var newBook = bookProvider.saveBook(book);
        eventPublisher.publishEvent(createBookEvent(newBook, BookEventType.CREATED, clock.instant()));
        return toResponse(newBook);
    }

    public BookResponse updateBookStatusRented(String isbn, String borrowerName) {
        var rentedBook = bookProvider.rentBook(isbn, borrowerName);
        eventPublisher.publishEvent(createBookEvent(rentedBook, BookEventType.UPDATED, clock.instant()));
        return toResponse(rentedBook);
    }
}
