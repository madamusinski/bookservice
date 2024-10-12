package pl.madamusinski.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.madamusinski.book.model.Book;
import pl.madamusinski.book.repository.BookRentalInformationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookRentalInformationService {

    private final BookRentalInformationRepository bookRepository;

    public List<Book> getAllRentedBooks() {
        return bookRepository.getAllRentedBooks();
    }

    public void createBook(Book book) {
        bookRepository.saveBook(book);
    }

    @Transactional
    public void updateBookStatusRented(Book book) {
        var possibleBook = Optional.ofNullable(bookRepository.getBookByIsbn(book.isbn()));
        possibleBook.ifPresentOrElse(b -> addBorrowerAndSave(b, book.borrower()), this::rollbackActionOnBookManagementService);
    }

    private void addBorrowerAndSave(Book book, String borrowerName) {
        var updatedBook = Book.copyFrom(book).borrower(borrowerName).build();
        bookRepository.saveBook(updatedBook);
    }

    private void rollbackActionOnBookManagementService() {
        //add rollback action on book management service to unset borrower in MongoDB
    }
}
