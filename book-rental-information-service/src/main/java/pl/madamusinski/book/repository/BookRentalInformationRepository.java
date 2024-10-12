package pl.madamusinski.book.repository;

import pl.madamusinski.book.model.Book;

import java.util.List;

public interface BookRentalInformationRepository {

    List<Book> getAllRentedBooks();
    Book getBookByIsbn(String isbn);
    void saveBook(Book book);
}
