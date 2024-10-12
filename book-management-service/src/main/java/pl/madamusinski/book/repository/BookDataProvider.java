package pl.madamusinski.book.repository;

import pl.madamusinski.book.model.Book;

import java.util.List;

public interface BookDataProvider {

    List<Book> getAllBooks();
    Book saveBook(Book book);
    Book rentBook(String isbn, String borrowerName);
}
